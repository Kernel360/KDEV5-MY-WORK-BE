package kr.mywork.interfaces.dashboard.controller.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import kr.mywork.domain.project.service.dto.response.NearDeadlineProjectResponse;

public record NearDeadlineProjectWebResponse(
	UUID id,
	String name,
	LocalDateTime endAt,
	int dday
) {
	public static NearDeadlineProjectWebResponse fromServiceResponse(NearDeadlineProjectResponse nearDeadlineProjectResponse) {
		return new NearDeadlineProjectWebResponse(
			nearDeadlineProjectResponse.id(),
			nearDeadlineProjectResponse.name(),
			nearDeadlineProjectResponse.endAt(),
			nearDeadlineProjectResponse.dday()
		);
	}
}
