package kr.mywork.domain.project_checklist.service.dto.response;

import java.util.UUID;

public record ProjectStepCheckListCountResponse(UUID projectStepId, Long count, Integer orderNum) {
}
