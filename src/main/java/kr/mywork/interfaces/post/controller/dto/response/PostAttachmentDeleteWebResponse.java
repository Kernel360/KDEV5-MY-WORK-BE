package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.PostAttachmentDeleteResponse;

public record PostAttachmentDeleteWebResponse(UUID postAttachmentId, Boolean deleted) {

	public static PostAttachmentDeleteWebResponse fromServiceDto(
		final PostAttachmentDeleteResponse postAttachmentDeleteResponse) {
		return new PostAttachmentDeleteWebResponse(
			postAttachmentDeleteResponse.postAttachmentId(),
			postAttachmentDeleteResponse.deleted());
	}
}
