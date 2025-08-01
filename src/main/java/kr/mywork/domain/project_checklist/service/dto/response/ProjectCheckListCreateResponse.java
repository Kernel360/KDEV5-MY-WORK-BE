package kr.mywork.domain.project_checklist.service.dto.response;

import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;

public record ProjectCheckListCreateResponse(String title, String content, UUID projectStepId, String approval) {

	public static ProjectCheckListCreateResponse from(ProjectCheckList projectCheckList) {
		return new ProjectCheckListCreateResponse(projectCheckList.getTitle(), projectCheckList.getContent(), projectCheckList.getProjectStepId(),
			projectCheckList.getApproval());
	}

}
