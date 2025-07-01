package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

public record PostAttachmentUploadUrlIssueResponse(UUID postAttachmentId, String uploadUrl) {
}
