package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

public record PostAttachmentDownloadResponse(UUID postAttachmentId, String downloadUrl) {
}
