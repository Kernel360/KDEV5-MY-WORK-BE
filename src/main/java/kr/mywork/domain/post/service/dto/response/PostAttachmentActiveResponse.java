package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

public record PostAttachmentActiveResponse(UUID postAttachmentId, boolean active) {
}
