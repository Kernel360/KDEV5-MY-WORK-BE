package kr.mywork.interfaces.project.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project.service.dto.response.ProjectSelectResponse;

public record ProjectSelectWebResponse(
	UUID id,
	String name,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startAt,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endAt,
	String step,
	UUID devCompanyId,
	String devCompanyName,
	UUID clientCompanyId,
	String clientCompanyName) {

	public static ProjectSelectWebResponse from(ProjectSelectResponse response) {
		return new ProjectSelectWebResponse(
			response.id(),
			response.name(),
			response.startAt(),
			response.endAt(),
			response.step(),
			response.devCompanyId(),
			response.devCompanyName(),
			response.clientCompanyId(),
			response.clientCompanyName()
		);
	}
}
