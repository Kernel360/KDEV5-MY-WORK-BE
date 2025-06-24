package kr.mywork.interfaces.post.controller;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.service.PostAttachmentUploadService;
import kr.mywork.domain.post.service.dto.response.PostAttachmentActiveResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentDeleteResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentDownloadResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentUploadUrlIssueResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentUploadUrlReissueResponse;
import kr.mywork.interfaces.post.controller.dto.request.PostAttachmentActiveWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostAttachmentUploadUrlIssueWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostAttachmentUploadUrlReissueWebRequest;
import kr.mywork.interfaces.post.controller.dto.response.PostAttachmentActiveWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostAttachmentDeleteWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostAttachmentDownloadWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostAttachmentUploadUrlIssueWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostAttachmentUploadUrlReissueWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class PostAttachmentController {

	private final PostAttachmentUploadService postAttachmentUploadService;

	@PostMapping("/posts/attachment/upload-url/issue")
	public ApiResponse<PostAttachmentUploadUrlIssueWebResponse> issueUploadUrl(
		@RequestBody @Valid PostAttachmentUploadUrlIssueWebRequest postAttachmentUploadUrlIssueWebRequest) {

		final PostAttachmentUploadUrlIssueResponse postAttachmentUploadUrlIssueResponse =
			postAttachmentUploadService.issuePostAttachmentUploadUrl(
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
			postAttachmentUploadService.reissuePostAttachmentUploadUrl(
				postAttachmentUploadUrlIssueWebRequest.getPostAttachmentId(),
				postAttachmentUploadUrlIssueWebRequest.getFileName());

		return ApiResponse.success(new PostAttachmentUploadUrlReissueWebResponse(
			postAttachmentUploadUrlIssueResponse.postAttachmentId(),
			postAttachmentUploadUrlIssueWebRequest.getFileName()));
	}

	@PostMapping("/posts/attachment/active")
	public ApiResponse<PostAttachmentActiveWebResponse> activePostAttachment(
		@RequestBody @Valid PostAttachmentActiveWebRequest postAttachmentActiveWebRequest) {

		final PostAttachmentActiveResponse postAttachmentActiveResponse = postAttachmentUploadService.updatePostAttachmentActive(
			postAttachmentActiveWebRequest.getPostAttachmentId(),
			postAttachmentActiveWebRequest.getActive());

		return ApiResponse.success(new PostAttachmentActiveWebResponse(
			postAttachmentActiveResponse.postAttachmentId(),
			postAttachmentActiveResponse.active()));
	}

	@GetMapping("/posts/attachment/download-url")
	public ApiResponse<PostAttachmentDownloadWebResponse> issuePostAttachmentDownloadUrl(
		@RequestParam("postAttachmentId") final UUID postAttachmentId) {

		final PostAttachmentDownloadResponse postAttachmentDownloadResponse =
			postAttachmentUploadService.issueDownloadUrl(postAttachmentId);

		final PostAttachmentDownloadWebResponse postAttachmentDownloadWebResponse =
			PostAttachmentDownloadWebResponse.fromServiceDto(postAttachmentDownloadResponse);

		return ApiResponse.success(postAttachmentDownloadWebResponse);
	}

	@DeleteMapping("/posts/attachment/{postAttachmentId}")
	public ApiResponse<PostAttachmentDeleteWebResponse> deletePostAttachment(
		@PathVariable final UUID postAttachmentId) {

		final PostAttachmentDeleteResponse postAttachmentDeleteResponse =
			postAttachmentUploadService.deletePostAttachment(postAttachmentId);

		return ApiResponse.success(PostAttachmentDeleteWebResponse.fromServiceDto(postAttachmentDeleteResponse));
	}
}
