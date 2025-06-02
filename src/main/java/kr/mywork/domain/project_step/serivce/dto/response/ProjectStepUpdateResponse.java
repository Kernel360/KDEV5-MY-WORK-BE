package kr.mywork.domain.project_step.serivce.dto.response;

import java.util.UUID;

import kr.mywork.domain.project_step.model.ProjectStep;

public record ProjectStepUpdateResponse(UUID projectStepId, String title, Integer orderNumber) {
	public static ProjectStepUpdateResponse fromEntity(ProjectStep projectStep) {
		return new ProjectStepUpdateResponse(projectStep.getId(), projectStep.getTitle(), projectStep.getOrderNum());
	}
}
