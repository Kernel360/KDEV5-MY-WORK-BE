package kr.mywork.interfaces.project.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.mywork.domain.project.service.dto.response.ProjectUpdateResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectUpdateWebResponse(
	UUID id,
	String name,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startAt,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endAt,
	String step,
	String detail,
	Boolean deleted,
	Long projectAmount
) {
	public static ProjectUpdateWebResponse from(ProjectUpdateResponse response) {
		return new ProjectUpdateWebResponse(
			response.id(),
			response.name(),
			response.startAt(),
			response.endAt(),
			response.step(),
			response.detail(),
			response.deleted(),
			response.projectAmount()
		);
	}
}
