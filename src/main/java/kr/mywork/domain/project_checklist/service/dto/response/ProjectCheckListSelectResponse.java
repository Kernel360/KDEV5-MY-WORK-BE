package kr.mywork.domain.project_checklist.service.dto.response;

import java.time.LocalDateTime;

public record ProjectCheckListSelectResponse(
	String checkListName, String approval, String projectStepName, LocalDateTime createdAt) {
}
