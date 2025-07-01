package kr.mywork.domain.activityLog.service.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import kr.mywork.domain.activityLog.model.ActionType;
import kr.mywork.domain.activityLog.model.TargetType;
import lombok.Getter;

@Getter
public class ActivityLogSelectResponse {

	private final UUID id;

	private final LocalDateTime actionTime;

	private final ActionType actionType;

	private final TargetType targetType;

	private final String actorName;

	private final String actorCompanyName;

	private List<LogDetailSelectResponse> logDetails;

	public ActivityLogSelectResponse(UUID id, LocalDateTime actionTime, ActionType actionType,
		TargetType targetType, String actorName, String actorCompanyName) {
		this.id = id;
		this.actionTime = actionTime;
		this.actionType = actionType;
		this.targetType = targetType;
		this.actorName = actorName;
		this.actorCompanyName = actorCompanyName;
	}

	public void attachLogDetails(List<LogDetailSelectResponse> logDetails) {
		this.logDetails = logDetails;
	}
}