package kr.mywork.domain.project_checklist.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;

public record ProjectCheckListUpdateResponse(UUID id, String title, String content, LocalDateTime createdAt, String approval) {
	public static ProjectCheckListUpdateResponse from(ProjectCheckList projectCheckList) {
		return new ProjectCheckListUpdateResponse(projectCheckList.getId(), projectCheckList.getTitle(), projectCheckList.getContent(), projectCheckList.getCreatedAt(), projectCheckList.getApproval());
	}
}
