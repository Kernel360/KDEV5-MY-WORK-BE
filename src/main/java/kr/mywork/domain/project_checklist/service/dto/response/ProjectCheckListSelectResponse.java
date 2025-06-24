package kr.mywork.domain.project_checklist.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectCheckListSelectResponse(
	UUID id, String checkListName, String checkListContent, String approval, String projectStepName, LocalDateTime createdAt) {
}
