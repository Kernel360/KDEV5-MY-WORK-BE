package kr.mywork.domain.project_checklist.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectCheckListSelectResponse(
	UUID id, String authorName, String title, String content, String approval, String projectStepName, LocalDateTime createdAt) {
}
