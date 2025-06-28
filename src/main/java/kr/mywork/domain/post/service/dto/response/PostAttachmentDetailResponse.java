package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

public record PostAttachmentDetailResponse(UUID postAttachmentId, String fileName) {
}
