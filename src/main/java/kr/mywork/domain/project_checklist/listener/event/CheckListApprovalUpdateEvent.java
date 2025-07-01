package kr.mywork.domain.project_checklist.listener.event;

import java.util.UUID;

public record CheckListApprovalUpdateEvent(UUID checkListId, String approval, String reason,
										   String companyName, String authorName) {
}
