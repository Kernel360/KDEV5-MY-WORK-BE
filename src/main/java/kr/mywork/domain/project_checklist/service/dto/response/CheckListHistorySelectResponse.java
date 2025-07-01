package kr.mywork.domain.project_checklist.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CheckListHistorySelectResponse(UUID historyId, String companyName, String memberName, String reason,
											 String approval, LocalDateTime createdAt) {
}
