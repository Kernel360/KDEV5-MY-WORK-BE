package kr.mywork.domain.project_checklist.service.dto.response;

import java.util.UUID;

public record CheckListProjectStepProgressResponse(UUID projectStepId, String projectStepName, Long totalCount, Long approvalCount) {
}
