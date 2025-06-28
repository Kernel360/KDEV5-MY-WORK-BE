package kr.mywork.domain.post.listener.event;

import java.util.UUID;

public record PostAttachmentDeleteEvent(UUID postId) {
}
