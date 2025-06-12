package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListSelectResponse;

public record ProjectCheckListSelectWebResponse(
	String checkListName, String approval, String projectStepName,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt) {

	public static ProjectCheckListSelectWebResponse fromServiceResponse(
		ProjectCheckListSelectResponse projectCheckListSelectResponse) {
		return new ProjectCheckListSelectWebResponse(
			projectCheckListSelectResponse.checkListName(),
			projectCheckListSelectResponse.approval(),
			projectCheckListSelectResponse.projectStepName(),
			projectCheckListSelectResponse.createdAt());
	}
}
