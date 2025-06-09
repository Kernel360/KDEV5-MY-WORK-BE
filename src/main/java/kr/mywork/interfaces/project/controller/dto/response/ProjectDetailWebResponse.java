package kr.mywork.interfaces.project.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project.service.dto.response.ProjectDetailResponse;

public record ProjectDetailWebResponse(
	UUID projectId, String name, @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime startAt,
	@JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime endAt, String step, String detail,
	Boolean deleted, @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime createdAt, UUID devCompanyId,
	String devCompanyName, String devContactPhoneNum, UUID clientCompanyId, String clientCompanyName,
	String clientContactPhoneNum
) {
	public static ProjectDetailWebResponse from(ProjectDetailResponse response) {
		return new ProjectDetailWebResponse(
			response.projectId(),
			response.name(),
			response.startAt(),
			response.endAt(),
			response.step(),
			response.detail(),
			response.deleted(),
			response.createdAt(),
			response.devCompanyId(),
			response.devCompanyName(),
			response.devContactPhoneNum(),
			response.clientCompanyId(),
			response.clientCompanyName(),
			response.clientContactPhoneNum());
	}
}
