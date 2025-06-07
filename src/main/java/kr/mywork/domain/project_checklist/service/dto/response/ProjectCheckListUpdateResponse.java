package kr.mywork.domain.project_checklist.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;

public record ProjectCheckListUpdateResponse(UUID id, String title, LocalDateTime createdAt, String approval) {
	public static ProjectCheckListUpdateResponse from(ProjectCheckList projectCheckList) {
		return new ProjectCheckListUpdateResponse(projectCheckList.getId(), projectCheckList.getTitle(), projectCheckList.getCreatedAt(), projectCheckList.getApproval());
	}
}
