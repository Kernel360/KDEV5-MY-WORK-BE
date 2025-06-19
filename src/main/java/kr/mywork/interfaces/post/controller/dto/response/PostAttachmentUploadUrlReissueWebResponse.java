package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

public record PostAttachmentUploadUrlReissueWebResponse(UUID postAttachmentId, String uploadUrl) {
}
