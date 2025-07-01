package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

public record PostAttachmentUploadUrlReissueResponse(UUID postAttachmentId, String uploadUrl) {
}
