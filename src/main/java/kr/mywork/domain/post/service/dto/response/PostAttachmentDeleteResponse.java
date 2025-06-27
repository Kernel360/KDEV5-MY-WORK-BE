package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

public record PostAttachmentDeleteResponse(UUID postAttachmentId, boolean deleted) {
}
