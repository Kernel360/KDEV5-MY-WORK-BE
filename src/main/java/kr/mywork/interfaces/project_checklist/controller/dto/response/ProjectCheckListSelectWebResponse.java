package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListSelectResponse;

public record ProjectCheckListSelectWebResponse(
	UUID id, String checkListName, String checkListContent, String approval, String projectStepName,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt) {

	public static ProjectCheckListSelectWebResponse fromServiceResponse(
		ProjectCheckListSelectResponse projectCheckListSelectResponse) {
		return new ProjectCheckListSelectWebResponse(
			projectCheckListSelectResponse.id(),
			projectCheckListSelectResponse.checkListName(),
			projectCheckListSelectResponse.checkListContent(),
			projectCheckListSelectResponse.approval(),
			projectCheckListSelectResponse.projectStepName(),
			projectCheckListSelectResponse.createdAt());
	}
}
