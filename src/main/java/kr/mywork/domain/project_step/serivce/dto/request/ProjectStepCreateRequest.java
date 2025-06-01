package kr.mywork.domain.project_step.serivce.dto.request;

import kr.mywork.domain.project_step.model.ProjectStep;

public record ProjectStepCreateRequest(String title, Integer orderNum) {

	public ProjectStep toEntity() {
		return new ProjectStep(this.title, this.orderNum);
	}
}
