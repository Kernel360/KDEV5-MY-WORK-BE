package kr.mywork.domain.post.service.dto.request;

import java.util.UUID;

public record ReviewDeleteRequest(UUID memberId, UUID reviewId) {
}
