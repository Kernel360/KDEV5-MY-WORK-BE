package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListSelectResponse;

public record ProjectCheckListSelectWebResponse(
	UUID id, String authorName, String title, String content, String approval, String projectStepName,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt) {

	public static ProjectCheckListSelectWebResponse fromServiceResponse(
		ProjectCheckListSelectResponse projectCheckListSelectResponse) {
		return new ProjectCheckListSelectWebResponse(
			projectCheckListSelectResponse.id(),
			projectCheckListSelectResponse.authorName(),
			projectCheckListSelectResponse.title(),
			projectCheckListSelectResponse.content(),
			projectCheckListSelectResponse.approval(),
			projectCheckListSelectResponse.projectStepName(),
			projectCheckListSelectResponse.createdAt());
	}
}
