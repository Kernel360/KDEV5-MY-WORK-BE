package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.PostAttachmentActiveResponse;

public record PostAttachmentActiveWebResponse(UUID postAttachmentId, boolean active) {

	public static PostAttachmentActiveWebResponse fromServiceDto(
		PostAttachmentActiveResponse postAttachmentActiveResponse) {

		return new PostAttachmentActiveWebResponse(
			postAttachmentActiveResponse.postAttachmentId(),
			postAttachmentActiveResponse.active());
	}
}
