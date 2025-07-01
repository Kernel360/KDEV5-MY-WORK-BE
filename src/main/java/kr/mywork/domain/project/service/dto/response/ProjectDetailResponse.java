package kr.mywork.domain.project.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectDetailResponse(
	UUID projectId, String name, LocalDateTime startAt, LocalDateTime endAt, String step, String detail,
	Boolean deleted, LocalDateTime createdAt, Long projectAmount, UUID devCompanyId, String devCompanyName, String devContactPhoneNum,
	UUID clientCompanyId, String clientCompanyName, String clientContactPhoneNum
) {
}
