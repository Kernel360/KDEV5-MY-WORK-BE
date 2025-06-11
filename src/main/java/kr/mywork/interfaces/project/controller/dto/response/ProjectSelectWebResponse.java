package kr.mywork.interfaces.project.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project.service.dto.response.ProjectSelectWithAssignResponse;

public record ProjectSelectWebResponse(
	UUID id,
	String name,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startAt,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endAt,
	String step,
	String detail,
	Boolean deleted,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt,
	UUID devCompanyId,
	UUID clientCompanyId
) {
	public static ProjectSelectWebResponse from(ProjectSelectWithAssignResponse response) {
		return new ProjectSelectWebResponse(
			response.id(),
			response.name(),
			response.startAt(),
			response.endAt(),
			response.step(),
			response.detail(),
			response.deleted(),
			response.createdAt(),
			response.devCompanyId(),
			response.clientCompanyId()
		);
	}
}
