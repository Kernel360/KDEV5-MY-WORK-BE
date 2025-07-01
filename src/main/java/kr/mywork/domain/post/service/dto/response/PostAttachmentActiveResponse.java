package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.model.PostAttachment;

public record PostAttachmentActiveResponse(UUID postAttachmentId, boolean active) {

	public static PostAttachmentActiveResponse fromEntity(final PostAttachment postAttachment) {
		return new PostAttachmentActiveResponse(postAttachment.getId(), postAttachment.isActive());
	}
}
