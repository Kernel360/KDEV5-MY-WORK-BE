package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListDetailResponse;

public record ProjectCheckListDetailWebResponse(UUID id, String title, String content, String companyName, String authorName, String approval, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt) {

	public ProjectCheckListDetailWebResponse(ProjectCheckListDetailResponse response) {
		this(response.id(), response.title(), response.content(), response.authorName(), response.companyName(),  response.approval(), response.createdAt());
	}
}
