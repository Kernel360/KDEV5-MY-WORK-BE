package kr.mywork.domain.project.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectSelectResponse(
	UUID id, String name, LocalDateTime startAt, LocalDateTime endAt, String step,
	UUID devCompanyId, String devCompanyName, UUID clientCompanyId, String clientCompanyName) {

	public static ProjectSelectResponse of(
		UUID projectId, String projectName, LocalDateTime startAt, LocalDateTime endAt, String step,
		UUID devCompanyId, String devCompanyName, UUID clientCompanyId, String clientCompanyName) {
		return new ProjectSelectResponse(projectId, projectName, startAt, endAt, step,
			devCompanyId, devCompanyName, clientCompanyId, clientCompanyName);
	}
}
