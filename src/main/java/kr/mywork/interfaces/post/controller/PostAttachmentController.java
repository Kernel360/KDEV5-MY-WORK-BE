package kr.mywork.interfaces.post.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.service.PostService;
import kr.mywork.domain.post.service.dto.response.PostAttachmentUploadUrlIssueResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentUploadUrlReissueResponse;
import kr.mywork.interfaces.post.controller.dto.request.PostAttachmentUploadUrlIssueWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostAttachmentUploadUrlReissueWebRequest;
import kr.mywork.interfaces.post.controller.dto.response.PostAttachmentUploadUrlIssueWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostAttachmentUploadUrlReissueWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class PostAttachmentController {

	private final PostService postService;

	@PostMapping("/posts/attachment/upload-url/issue")
	public ApiResponse<PostAttachmentUploadUrlIssueWebResponse> issueUploadUrl(
		@RequestBody @Valid PostAttachmentUploadUrlIssueWebRequest postAttachmentUploadUrlIssueWebRequest) {

		final PostAttachmentUploadUrlIssueResponse postAttachmentUploadUrlIssueResponse =
			postService.issuePostAttachmentUploadUrl(
				postAttachmentUploadUrlIssueWebRequest.getPostId(),
				postAttachmentUploadUrlIssueWebRequest.getFileName());

		return ApiResponse.success(
			new PostAttachmentUploadUrlIssueWebResponse(
				postAttachmentUploadUrlIssueResponse.postAttachmentId(),
				postAttachmentUploadUrlIssueResponse.uploadUrl()));
	}

	@PostMapping("/posts/attachment/upload-url/reissue")
	public ApiResponse<PostAttachmentUploadUrlReissueWebResponse> reissueUploadUrl(
		@RequestBody @Valid PostAttachmentUploadUrlReissueWebRequest postAttachmentUploadUrlIssueWebRequest) {

		final PostAttachmentUploadUrlReissueResponse postAttachmentUploadUrlIssueResponse =
			postService.reissuePostAttachmentUploadUrl(
				postAttachmentUploadUrlIssueWebRequest.getPostAttachmentId(),
				postAttachmentUploadUrlIssueWebRequest.getFileName());

		return ApiResponse.success(new PostAttachmentUploadUrlReissueWebResponse(
			postAttachmentUploadUrlIssueResponse.postAttachmentId(),
			postAttachmentUploadUrlIssueWebRequest.getFileName()));
	}

}
