package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

public record PostAttachmentActiveWebResponse(UUID postAttachmentId, boolean active) {
}
