package kr.mywork.domain.project_checklist.service.dto.response;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;

public record ProjectCheckListDetailResponse(String title, String content, String approval) {

	public ProjectCheckListDetailResponse(ProjectCheckList projectCheckList) {
		this(projectCheckList.getTitle(), projectCheckList.getContent(), projectCheckList.getApproval());
	}
}
