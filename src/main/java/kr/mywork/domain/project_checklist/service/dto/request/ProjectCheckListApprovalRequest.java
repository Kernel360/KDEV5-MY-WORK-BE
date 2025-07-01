package kr.mywork.domain.project_checklist.service.dto.request;

import java.util.UUID;

public record ProjectCheckListApprovalRequest(UUID id, String approval, String reason) {
}
