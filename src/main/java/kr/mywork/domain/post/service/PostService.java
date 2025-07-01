package kr.mywork.domain.post.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.activityLog.listener.eventObject.CreateEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.DeleteEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.ModifyEventObject;
import kr.mywork.domain.notification.model.NotificationActionType;
import kr.mywork.domain.notification.model.TargetType;
import kr.mywork.domain.post.errors.PostDeletedException;
import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostIdNotFoundException;
import kr.mywork.domain.post.errors.PostNotFoundException;
import kr.mywork.domain.post.listener.event.PostApprovalAlarmEvent;
import kr.mywork.domain.post.listener.event.PostAttachmentDeleteEvent;
import kr.mywork.domain.post.listener.event.PostReviewsDeleteEvent;
import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.model.PostAttachment;
import kr.mywork.domain.post.repository.PostAttachmentRepository;
import kr.mywork.domain.post.repository.PostIdRepository;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import kr.mywork.domain.post.service.dto.request.PostUpdateRequest;
import kr.mywork.domain.post.service.dto.response.PostApprovalRequest;
import kr.mywork.domain.post.service.dto.response.PostApprovalResponse;
import kr.mywork.domain.post.service.dto.response.PostDetailResponse;
import kr.mywork.domain.post.service.dto.response.PostSelectResponse;
import kr.mywork.domain.post.service.dto.response.PostTotalCountInStepResponse;
import kr.mywork.domain.post.service.dto.response.PostUpdateResponse;
import kr.mywork.domain.project.errors.ProjectErrorType;
import kr.mywork.domain.project.errors.ProjectNotFoundException;
import kr.mywork.domain.project.repository.ProjectRepository;
import kr.mywork.domain.project_step.errors.ProjectStepErrorType;
import kr.mywork.domain.project_step.errors.ProjectStepNotFoundException;
import kr.mywork.domain.project_step.model.ProjectStep;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepDetailRequest;
import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepPostTotalCountResponse;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PostService {

	@Value("${post.page.size}")
	private int postPageSize;

	private final PostRepository postRepository;
	private final PostIdRepository postIdRepository;
	private final ProjectStepRepository projectStepRepository;
	private final ProjectRepository projectRepository;
	private final PostAttachmentRepository postAttachmentRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public PostApprovalResponse approvalPost(UUID postId, PostApprovalRequest postApprovalRequest, LoginMemberDetail loginMemberDetail) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(PostErrorType.POST_NOT_FOUND));

		Post before = Post.copyOf(post);

		ProjectStep projectStep = projectStepRepository.findById(post.getProjectStepId())
			.orElseThrow(() -> new ProjectStepNotFoundException(ProjectStepErrorType.PROJECT_STEP_NOT_FOUND));

		post.changeApproval(postApprovalRequest.getApprovalStatus());

		final PostApprovalAlarmEvent postApprovalAlarmEvent = new PostApprovalAlarmEvent(
			post.getAuthorId(), post.getAuthorName(),
			post.getTitle(),
			loginMemberDetail.memberId(),
			loginMemberDetail.memberName(),
			TargetType.POST,
			post.getId(),
			post.isApproved() ? NotificationActionType.APPROVED : NotificationActionType.PENDING,
			post.getModifiedAt(),
			projectStep.getProjectId(),
			projectStep.getId());

		eventPublisher.publishEvent(postApprovalAlarmEvent);
		eventPublisher.publishEvent(new ModifyEventObject(before, post, loginMemberDetail));

		return new PostApprovalResponse(post.getId(), post.getApproval());
	}

	@Transactional
	public UUID createPost(PostCreateRequest postCreateRequest, LoginMemberDetail loginMemberDetail) {
		postIdRepository.findById(postCreateRequest.getId())
			.orElseThrow(() -> new PostIdNotFoundException(PostErrorType.ID_NOT_FOUND));

		final Post savedPost = postRepository.save(postCreateRequest);

		eventPublisher.publishEvent(new CreateEventObject(savedPost, loginMemberDetail));

		return savedPost.getId();
	}

	@Transactional
	public PostUpdateResponse updatePost(PostUpdateRequest postUpdateRequest, LoginMemberDetail loginMemberDetail) {
		Post post = postRepository.findById(postUpdateRequest.getId())
			.orElseThrow(() -> new PostNotFoundException(PostErrorType.POST_NOT_FOUND));

		Post before = Post.copyOf(post);
		post.update(postUpdateRequest);

		eventPublisher.publishEvent(new ModifyEventObject(before, post, loginMemberDetail));
		return PostUpdateResponse.from(post);
	}

	@Transactional
	public PostDetailResponse getPostDetail(UUID postId) {
		final Post post = postRepository.findById(postId)
			.orElseThrow(() -> new PostNotFoundException(PostErrorType.POST_NOT_FOUND));

		if (post.isDeleted()) {
			throw new PostDeletedException(PostErrorType.POST_DELETED);
		}

		final List<PostAttachment> postAttachments =
			postAttachmentRepository.findAllByPostIdDeletedAndActive(postId, false, true);

		return PostDetailResponse.from(post, postAttachments);
	}

	@Transactional
	public List<PostSelectResponse> findPostsBySearchConditionWithPaging(final int page, final UUID projectStepId,
		final String keyword, final Boolean deleted, final UUID projectId, final String keywordType,
		final String approval) {

		// TODO projectId가 DB에 존재 하지 않을때 에러 처리해아함.
		projectRepository.findById(projectId).
			orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		List<PostSelectResponse> result;

		if (projectStepId != null) { // if (projectStepId != null) 프로젝트단계가 있으면 프로젝트단계에 해당하는 게시물만 조회

			// TODO projectStepId가 DB에 존재 하지 않을때 에러 처리해아함.
			ProjectStep findProjectStep = projectStepRepository.findById(projectStepId)
				.orElseThrow(() -> new ProjectStepNotFoundException(ProjectStepErrorType.PROJECT_STEP_NOT_FOUND));

			String projectStepTitle = findProjectStep.getTitle();
			result = postRepository.findPostsByProjectStepSearchConditionWithPaging(page, postPageSize, projectStepId,
				projectStepTitle, keyword, deleted, projectId, keywordType, approval);

		} else { // else () 프로젝트단계가 없으면 프로젝트의 게시물 전체 조회

			result = postRepository.findPostsByProjectSearchConditionWithPaging(page, postPageSize, keyword,
				deleted, projectId, keywordType, approval);

		}
		return result;
	}

	@Transactional(readOnly = true)
	public Long countTotalPostsByCondition(UUID projectStepId, String keyword, Boolean deleted, UUID projectId,
		String keywordType, String approval) {
		Long totalCount;

		if (projectStepId != null) {
			totalCount = postRepository.countTotalPostsByProjectStepCondition(projectStepId, keyword, deleted,
				projectId, keywordType, approval);
		} else {
			List<ProjectStep> projectSteps = projectStepRepository.findAllStepsByProjectIdOrderByNumAsc(projectId);
			totalCount = postRepository.countTotalPostsByProjectCondition(projectSteps, keyword, deleted, projectId,
				keywordType, approval);
		}
		return totalCount;
	}

	@Transactional
	public UUID deletePost(UUID postId, LoginMemberDetail loginMemberDetail) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new PostNotFoundException(PostErrorType.POST_NOT_FOUND));

		post.delete();

		eventPublisher.publishEvent(new DeleteEventObject(post, loginMemberDetail));
		eventPublisher.publishEvent(new PostAttachmentDeleteEvent(postId));
		eventPublisher.publishEvent(new PostReviewsDeleteEvent(postId));

		return post.getId();
	}

	@Transactional
	public List<ProjectStepPostTotalCountResponse> getProjectStepsWithPostTotalCount(List<ProjectStepDetailRequest> noneCountProjectSteps){
		final List<UUID> projectStepIds = noneCountProjectSteps.stream()
			.map(ProjectStepDetailRequest::projectStepId)
			.toList();

		final List<PostTotalCountInStepResponse> getPostTotalCount = postRepository.findPostCountGroupedByProjectStepId(projectStepIds);

		Map<UUID, Long> postCountMap = getPostTotalCount.stream()
			.collect(Collectors.toMap(
				PostTotalCountInStepResponse::projectStepId,
				PostTotalCountInStepResponse::totalCount
			));

		return noneCountProjectSteps.stream()
			.map(step -> new ProjectStepPostTotalCountResponse(
				step.projectStepId(),
				step.title(),
				step.orderNum(),
				postCountMap.getOrDefault(step.projectStepId(), 0L)
			))
			.toList();
	}
}
