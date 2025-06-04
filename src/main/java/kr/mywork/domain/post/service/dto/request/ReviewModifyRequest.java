package kr.mywork.domain.post.service.dto.request;

import java.util.UUID;

public record ReviewModifyRequest(UUID memberId, UUID reviewId, String comment) {
}
