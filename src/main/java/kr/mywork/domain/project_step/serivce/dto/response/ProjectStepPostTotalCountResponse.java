package kr.mywork.domain.project_step.serivce.dto.response;

import java.util.UUID;

public record ProjectStepPostTotalCountResponse(UUID projectStepId, String title, Integer orderNum, Long totalCount) {
}
