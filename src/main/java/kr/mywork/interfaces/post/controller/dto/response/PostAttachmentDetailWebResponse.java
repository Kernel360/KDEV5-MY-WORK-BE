package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.PostAttachmentDetailResponse;

public record PostAttachmentDetailWebResponse(UUID postAttachmentId, String fileName) {
	public static PostAttachmentDetailWebResponse fromServiceDto(PostAttachmentDetailResponse postAttachmentDetailResponse) {
		return new PostAttachmentDetailWebResponse(
			postAttachmentDetailResponse.postAttachmentId(),
			postAttachmentDetailResponse.fileName());
	}
}
