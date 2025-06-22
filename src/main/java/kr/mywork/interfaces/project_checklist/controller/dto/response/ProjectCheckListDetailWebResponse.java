package kr.mywork.interfaces.project_checklist.controller.dto.response;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListDetailResponse;

public record ProjectCheckListDetailWebResponse(String title, String content, String approval) {

	public ProjectCheckListDetailWebResponse(ProjectCheckListDetailResponse response) {
		this(response.title(), response.content(), response.approval());
	}
}
