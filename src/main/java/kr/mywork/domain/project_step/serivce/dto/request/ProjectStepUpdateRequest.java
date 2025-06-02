package kr.mywork.domain.project_step.serivce.dto.request;

import java.util.UUID;

public record ProjectStepUpdateRequest(UUID projectStepId, String title, Integer orderNum) {
}
