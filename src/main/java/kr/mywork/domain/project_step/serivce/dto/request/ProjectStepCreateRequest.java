package kr.mywork.domain.project_step.serivce.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.project_step.model.ProjectStep;

public record ProjectStepCreateRequest(String title, Integer orderNum) {

	public ProjectStep toEntity(UUID projectId) {
		return new ProjectStep(this.title, this.orderNum,projectId, LocalDateTime.now());
	}
}
