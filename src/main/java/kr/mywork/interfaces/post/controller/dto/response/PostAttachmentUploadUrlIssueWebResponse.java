package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

public record PostAttachmentUploadUrlIssueWebResponse(UUID postAttachmentId, String uploadUrl) {
}
