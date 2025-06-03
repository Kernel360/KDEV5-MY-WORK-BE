package kr.mywork.domain.project_step.serivce.dto.response;

import java.util.UUID;

import kr.mywork.domain.project_step.model.ProjectStep;

public record ProjectStepGetResponse(UUID projectStepId, String title, Integer orderNum) {

	public static ProjectStepGetResponse fromEntity(ProjectStep projectStep) {
		return new ProjectStepGetResponse(projectStep.getId(), projectStep.getTitle(), projectStep.getOrderNum());
	}
}
