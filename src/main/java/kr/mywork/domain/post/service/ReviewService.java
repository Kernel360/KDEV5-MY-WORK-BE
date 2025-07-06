package kr.mywork.domain.post.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogCreateEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogDeleteEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityModifyEvent;
import kr.mywork.domain.notification.listener.event.NotificationCreateEvent;
import kr.mywork.domain.notification.model.NotificationActionType;
import kr.mywork.domain.notification.model.TargetType;
import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostNotFoundException;
import kr.mywork.domain.post.errors.review.ReviewErrorType;
import kr.mywork.domain.post.errors.review.ReviewNotFoundException;
import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.model.Review;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.repository.ReviewRepository;
import kr.mywork.domain.post.service.dto.ReviewSelectResponse;
import kr.mywork.domain.post.service.dto.request.ReviewCreateRequest;
import kr.mywork.domain.post.service.dto.request.ReviewDeleteRequest;
import kr.mywork.domain.post.service.dto.request.ReviewModifyRequest;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.domain.post.service.dto.response.ReviewDeleteResponse;
import kr.mywork.domain.post.service.dto.response.ReviewModifyResponse;
import kr.mywork.domain.project_step.errors.ProjectStepErrorType;
import kr.mywork.domain.project_step.errors.ProjectStepNotFoundException;
import kr.mywork.domain.project_step.model.ProjectStep;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

	@Value("${review.page.size}")
	private int reviewPageSize;

	private final ReviewRepository reviewRepository;
	private final PostRepository postRepository;
	private final ProjectStepRepository projectStepRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public ReviewCreateResponse save(final ReviewCreateRequest reviewCreateRequest,
		LoginMemberDetail loginMemberDetail) {
		final Post post = postRepository.findById(reviewCreateRequest.postId())
			.orElseThrow(() -> new PostNotFoundException(PostErrorType.POST_NOT_FOUND));

		final Review review = reviewCreateRequest.toEntity();
		final Review savedReview = reviewRepository.save(review);

		final ProjectStep projectStep = projectStepRepository.findById(post.getProjectStepId())
			.orElseThrow(() -> new ProjectStepNotFoundException(ProjectStepErrorType.PROJECT_STEP_NOT_FOUND));

		final UUID memberId = loginMemberDetail.memberId();
		final NotificationCreateEvent notificationCreateEvent = createNotificationCreateEvent(
			loginMemberDetail, post, projectStep);

		if (!post.isAuthor(memberId)) {
			sendNotificationCreateEvent(notificationCreateEvent);
		}

		sendActivityLogCreateEvent(loginMemberDetail, savedReview);
		return ReviewCreateResponse.fromEntity(savedReview);
	}

	private NotificationCreateEvent createNotificationCreateEvent(final LoginMemberDetail loginMemberDetail,
		final Post post, final ProjectStep projectStep) {
		return new NotificationCreateEvent(
			post.getAuthorId(), post.getAuthorName(), post.getTitle(), loginMemberDetail.memberName(),
			loginMemberDetail.memberId(), TargetType.POST, post.getId(), NotificationActionType.REVIEW,
			LocalDateTime.now(), projectStep.getProjectId(), projectStep.getId());
	}

	private void sendActivityLogCreateEvent(final LoginMemberDetail loginMemberDetail, final Review savedReview) {
		eventPublisher.publishEvent(new ActivityLogCreateEvent(savedReview, loginMemberDetail));
	}

	private void sendNotificationCreateEvent(final NotificationCreateEvent notificationCreateEvent) {
		eventPublisher.publishEvent(notificationCreateEvent);
	}

	public ReviewModifyResponse modifyComment(final ReviewModifyRequest reviewModifyRequest,
		LoginMemberDetail loginMemberDetail) {
		final Review review = reviewRepository.findById(reviewModifyRequest.reviewId())
			.orElseThrow(() -> new ReviewNotFoundException(ReviewErrorType.REVIEW_NOT_FOUND));

		Review before = Review.copyOf(review);

		review.modifyComment(reviewModifyRequest.comment());

		eventPublisher.publishEvent(new ActivityModifyEvent(before, review, loginMemberDetail));

		return new ReviewModifyResponse(review.getId(), review.getComment());
	}

	public ReviewDeleteResponse deleteReview(final ReviewDeleteRequest reviewDeleteRequest,
		LoginMemberDetail loginMemberDetail) {
		// TODO 본인 작성 검증 내용 추가
		final Review review = reviewRepository.findById(reviewDeleteRequest.reviewId())
			.orElseThrow(() -> new ReviewNotFoundException(ReviewErrorType.REVIEW_NOT_FOUND));

		review.delete();

		eventPublisher.publishEvent(new ActivityLogDeleteEvent(review, loginMemberDetail));

		return new ReviewDeleteResponse(review.getId());
	}

	public List<ReviewSelectResponse> findAllReviewsWithPaging(final UUID postId, final Integer pageNumber) {
		final List<ReviewSelectResponse> parentReviews = reviewRepository.findParentReviewsWithPaging(postId,
			pageNumber,
			reviewPageSize);

		final List<UUID> parentIds = parentReviews.stream().map(ReviewSelectResponse::getReviewId).toList();

		final Map<UUID, List<ReviewSelectResponse>> childReviewsMap = reviewRepository.findByIds(postId, parentIds)
			.stream().collect(Collectors.groupingBy(ReviewSelectResponse::getParentId));

		childReviewsMap.values()
			.forEach(childReviews -> childReviews.sort(
				Comparator.comparing(ReviewSelectResponse::getCreatedAt, Comparator.reverseOrder())));

		for (ReviewSelectResponse parentReview : parentReviews) {
			parentReview.setChildReviewSelectResponses(childReviewsMap.get(parentReview.getReviewId()));
		}

		return parentReviews;
	}

	public Long deletePostReviews(final UUID postId) {
		return reviewRepository.deletePostReviews(postId);
	}
}
