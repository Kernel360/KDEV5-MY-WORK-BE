package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListApprovalResponse;

public record ProjectCheckListApprovalWebResponse(UUID id, String title,
												  @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt,
												  String approval) {
	public ProjectCheckListApprovalWebResponse(ProjectCheckListApprovalResponse response) {
		this(response.id(), response.title(), response.createdAt(), response.approval());
	}
}
