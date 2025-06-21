package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

public record PostTotalCountInStepResponse(UUID projectStepId, Long totalCount) {
}
