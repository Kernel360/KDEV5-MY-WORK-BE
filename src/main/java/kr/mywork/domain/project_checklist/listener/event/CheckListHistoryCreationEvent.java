package kr.mywork.domain.project_checklist.listener.event;

import java.util.UUID;

public record CheckListHistoryCreationEvent(UUID checkListId, String companyName, String authorName, String approval) {
}
