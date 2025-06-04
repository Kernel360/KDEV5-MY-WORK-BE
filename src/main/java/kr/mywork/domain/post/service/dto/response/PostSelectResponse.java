package kr.mywork.domain.post.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostSelectResponse(UUID postId, LocalDateTime createAt, String authorName, String title, String approval) {
}
