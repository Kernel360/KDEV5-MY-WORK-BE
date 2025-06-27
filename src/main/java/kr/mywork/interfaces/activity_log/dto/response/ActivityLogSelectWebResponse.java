package kr.mywork.interfaces.activity_log.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.activityLog.model.ActionType;
import kr.mywork.domain.activityLog.model.TargetType;
import kr.mywork.domain.activityLog.service.dto.response.ActivityLogSelectResponse;

public record ActivityLogSelectWebResponse(
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime actionTime, ActionType actionType, TargetType targetType,
	String actorName, String actorCompanyName, List<LogDetailSelectWebResponse> logDetails) {

	public static ActivityLogSelectWebResponse from(ActivityLogSelectResponse activityLogSelectResponse) {
		return new ActivityLogSelectWebResponse(
			activityLogSelectResponse.getActionTime(),
			activityLogSelectResponse.getActionType(),
			activityLogSelectResponse.getTargetType(),
			activityLogSelectResponse.getActorName(),
			activityLogSelectResponse.getActorCompanyName(),
			activityLogSelectResponse.getLogDetails().stream().map(LogDetailSelectWebResponse::from).toList()
		);
	}


}
