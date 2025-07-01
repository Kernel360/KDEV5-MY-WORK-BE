package kr.mywork.domain.project_checklist.service.dto.request;

import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;

public record ProjectCheckListCreateRequest(UUID authorId, String authorName, UUID companyId, String companyName, String title, String content, UUID projectStepId, String approval) {

	public ProjectCheckList toEntity() {
		return new ProjectCheckList(
			this.authorId(),
			this.authorName(),
			this.companyId(),
			this.companyName(),
			this.title(),
			this.content(),
			this.projectStepId(),
			this.approval()
		);
	}
}
