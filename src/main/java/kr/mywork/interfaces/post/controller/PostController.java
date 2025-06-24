package kr.mywork.interfaces.post.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.post.service.PostService;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import kr.mywork.domain.post.service.dto.request.PostUpdateRequest;
import kr.mywork.domain.post.service.dto.response.PostApprovalRequest;
import kr.mywork.domain.post.service.dto.response.PostApprovalResponse;
import kr.mywork.domain.post.service.dto.response.PostDetailResponse;
import kr.mywork.domain.post.service.dto.response.PostSelectResponse;
import kr.mywork.domain.post.service.dto.response.PostUpdateResponse;
import kr.mywork.interfaces.post.controller.dto.request.PostApprovalWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostCreateWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostUpdateWebRequest;
import kr.mywork.interfaces.post.controller.dto.response.PostApprovalWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostCreateWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostDeleteWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostDetailWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostIdCreateWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostListSelectWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostSelectWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostUpdateWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class PostController {

	private static final String POST_KEYWORD_TYPE = "^(AUTHORNAME|TITLE)?$";
	private static final String POST_APPROVAL_TYPE = "^(APPROVED|PENDING)?$";

	private final PostService postService;

	@PostMapping("/posts/id/generate")
	public ApiResponse<PostIdCreateWebResponse> createPostId() {
		final UUID postId = postService.createPostId();
		return ApiResponse.success(new PostIdCreateWebResponse(postId));
	}

	@PostMapping("/projects/{project-id}/posts")
	public ApiResponse<PostCreateWebResponse> createPost(
		@PathVariable(name = "project-id") final UUID projectId,
		@RequestBody @Valid final PostCreateWebRequest postCreateWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {

		final PostCreateRequest postCreateRequest = postCreateWebRequest.toServiceDto(projectId);

		final UUID createdPostId = postService.createPost(postCreateRequest, loginMemberDetail);

		final PostCreateWebResponse postCreateWebResponse = new PostCreateWebResponse(createdPostId);

		return ApiResponse.success(postCreateWebResponse);

	}

	@PutMapping("/posts/{postId}")
	public ApiResponse<PostUpdateWebResponse> updatePost(
		@RequestBody @Valid final PostUpdateWebRequest postUpdateWebRequest,
		@PathVariable final UUID postId,
		@LoginMember LoginMemberDetail loginMemberDetail) {

		final PostUpdateRequest postUpdateRequest = postUpdateWebRequest.toServiceDto(postId);

		final PostUpdateResponse postUpdateResponse = postService.updatePost(postUpdateRequest, loginMemberDetail);

		final PostUpdateWebResponse postUpdateWebResponse = PostUpdateWebResponse.from(postUpdateResponse);

		return ApiResponse.success(postUpdateWebResponse);
	}

	@GetMapping("/posts/{postId}")
	public ApiResponse<PostDetailWebResponse> getPostDetail(@PathVariable @Valid final UUID postId) {

		PostDetailResponse postDetailResponse = postService.getPostDetail(postId);

		PostDetailWebResponse postDetailWebResponse = PostDetailWebResponse.from(postDetailResponse);

		return ApiResponse.success(postDetailWebResponse);
	}

	@GetMapping("/projects/{project-id}/posts")
	public ApiResponse<PostListSelectWebResponse> findPostsByOffset(
		@RequestParam(name = "page") @Min(value = 1, message = "{invalid.page-size}") final int page,
		@PathVariable(name = "project-id") final UUID projectId,
		@RequestParam(name = "keyword", required = false) final String keyword,
		@RequestParam(name = "keywordType", required = false) @Pattern(regexp = POST_KEYWORD_TYPE, message = "{invalid.post-search-type}") final String keywordType,
		@RequestParam(name = "projectStepId", required = false) final UUID projectStepId,
		@RequestParam(name = "deleted", required = false) final Boolean deleted,
		@RequestParam(name = "approval", required = false) @Pattern(regexp = POST_APPROVAL_TYPE, message = "{invalid.post-approval-type}") final String approval
	) {

		final List<PostSelectResponse> postSelectResponses = postService.findPostsBySearchConditionWithPaging(page,
			projectStepId, keyword, deleted, projectId, keywordType, approval);

		final Long totalCount = postService.countTotalPostsByCondition(projectStepId, keyword, deleted, projectId, keywordType, approval);

		final List<PostSelectWebResponse> postSelectWebResponses = postSelectResponses.stream()
			.map(PostSelectWebResponse::from)
			.toList();

		return ApiResponse.success(new PostListSelectWebResponse(postSelectWebResponses, totalCount));

	}

	@DeleteMapping("/posts/{postId}")
	public ApiResponse<PostDeleteWebResponse> deletePost(
		@PathVariable final UUID postId,
		@LoginMember LoginMemberDetail loginMemberDetail) {

		final UUID deletedPostId = postService.deletePost(postId, loginMemberDetail);
		final PostDeleteWebResponse postDeleteWebResponse = new PostDeleteWebResponse(deletedPostId);

		return ApiResponse.success(postDeleteWebResponse);
	}

	@PutMapping("/posts/{postId}/approval")
	public ApiResponse<PostApprovalWebResponse> approvalPost(
		@RequestBody @Valid PostApprovalWebRequest postApprovalWebRequest,
		@PathVariable final UUID postId,
		@LoginMember LoginMemberDetail loginMemberDetail) {
		PostApprovalRequest postApprovalRequest = postApprovalWebRequest.toServiceDto();

		PostApprovalResponse postApprovalResponse = postService.approvalPost(postId,
			postApprovalRequest, loginMemberDetail);

		return ApiResponse.success(new PostApprovalWebResponse(postApprovalResponse));
	}
}
