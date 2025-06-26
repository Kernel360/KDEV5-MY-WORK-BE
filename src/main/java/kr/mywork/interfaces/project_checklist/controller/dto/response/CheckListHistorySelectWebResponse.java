package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.project_checklist.service.dto.response.CheckListHistorySelectResponse;

public record CheckListHistorySelectWebResponse(UUID historyId, String companyName, String memberName, String reason,
												String approval,
												@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt) {

	public static CheckListHistorySelectWebResponse fromServiceDto(
		CheckListHistorySelectResponse checkListHistorySelectResponse) {
		return new CheckListHistorySelectWebResponse(
			checkListHistorySelectResponse.historyId(),
			checkListHistorySelectResponse.companyName(),
			checkListHistorySelectResponse.memberName(),
			checkListHistorySelectResponse.reason(),
			checkListHistorySelectResponse.approval(),
			checkListHistorySelectResponse.createdAt());
	}
}
