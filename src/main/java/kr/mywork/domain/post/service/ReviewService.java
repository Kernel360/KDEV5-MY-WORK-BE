package kr.mywork.domain.post.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.member.model.MemberRole;
import kr.mywork.domain.post.errors.review.ReviewErrorType;
import kr.mywork.domain.post.errors.review.ReviewNotFoundException;
import kr.mywork.domain.post.model.Review;
import kr.mywork.domain.post.repository.ReviewRepository;
import kr.mywork.domain.post.service.dto.ReviewSelectResponse;
import kr.mywork.domain.post.service.dto.request.ReviewCreateRequest;
import kr.mywork.domain.post.service.dto.request.ReviewDeleteRequest;
import kr.mywork.domain.post.service.dto.request.ReviewModifyRequest;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.domain.post.service.dto.response.ReviewDeleteResponse;
import kr.mywork.domain.post.service.dto.response.ReviewModifyResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

	@Value("${review.page.size}")
	private int reviewPageSize;

	private final ReviewRepository reviewRepository;

	public ReviewCreateResponse save(final ReviewCreateRequest reviewCreateRequest) {
		// TODO Post 조회 및 검증 로직 추가 필요

		final Review review = reviewCreateRequest.toEntity();
		final Review savedReview = reviewRepository.save(review);

		return ReviewCreateResponse.fromEntity(savedReview);
	}

	public ReviewModifyResponse modifyComment(final ReviewModifyRequest reviewModifyRequest) {
		final Review review = reviewRepository.findById(reviewModifyRequest.reviewId())
			.orElseThrow(() -> new ReviewNotFoundException(ReviewErrorType.REVIEW_NOT_FOUND));

		review.modifyComment(reviewModifyRequest.comment());

		return new ReviewModifyResponse(review.getId(), review.getComment());
	}

	public ReviewDeleteResponse deleteReview(final ReviewDeleteRequest reviewDeleteRequest) {
		// TODO 본인 작성 검증 내용 추가
		final Review review = reviewRepository.findById(reviewDeleteRequest.reviewId())
			.orElseThrow(() -> new ReviewNotFoundException(ReviewErrorType.REVIEW_NOT_FOUND));

		review.delete();

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
}
