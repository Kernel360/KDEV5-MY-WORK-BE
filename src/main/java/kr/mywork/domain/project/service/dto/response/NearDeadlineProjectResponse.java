package kr.mywork.domain.project.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record NearDeadlineProjectResponse(
	UUID id,
	String name,
	LocalDateTime endAt,
	int dday
) {
	public static NearDeadlineProjectResponse of(UUID id, String name, LocalDateTime endAt, int dday) {
		return new NearDeadlineProjectResponse(id, name, endAt, dday);
	}
}
