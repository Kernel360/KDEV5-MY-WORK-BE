package kr.mywork.domain.project.service.dto.request;

import java.time.LocalDateTime;

public record ProjectUpdateRequest(String name, LocalDateTime startAt, LocalDateTime endAt, String step, String detail,
								   Boolean deleted) {
}
