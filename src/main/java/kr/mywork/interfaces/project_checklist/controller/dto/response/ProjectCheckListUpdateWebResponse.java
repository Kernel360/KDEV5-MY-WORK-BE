package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListUpdateResponse;

public record ProjectCheckListUpdateWebResponse(UUID id, String title, String content,
												@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt,
												String approval) {
	public ProjectCheckListUpdateWebResponse(ProjectCheckListUpdateResponse response) {
		this(response.id(), response.title(), response.content(), response.createdAt(), response.approval());
	}
}
