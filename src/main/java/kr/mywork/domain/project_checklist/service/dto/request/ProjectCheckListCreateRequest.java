package kr.mywork.domain.project_checklist.service.dto.request;

import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;

public record ProjectCheckListCreateRequest(String title, UUID projectStepId, String approval) {

	public ProjectCheckList toEntity() {
		return new ProjectCheckList(this.title, this.projectStepId, this.approval);
	}
}
