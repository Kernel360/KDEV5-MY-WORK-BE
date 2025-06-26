package kr.mywork.domain.notification.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.notification.model.NotificationActionType;
import kr.mywork.domain.notification.model.TargetType;
import lombok.Getter;

@Getter
public class NotificationSelectResponse {

	private final UUID id;
	private final UUID actorId;
	private final String actorName;
	private final String actionType;
	private final String targetType;
	private final UUID targetId;
	private final String content;
	private final UUID projectId;
	private final UUID projectStepId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime actionTime;

	public NotificationSelectResponse(UUID id, UUID actorId, String actorName, NotificationActionType actionType,
		TargetType targetType, UUID targetId, String content,
		UUID projectId, UUID projectStepId, LocalDateTime actionTime) {
		this.id = id;
		this.actorId = actorId;
		this.actorName = actorName;
		this.actionType = actionType.name();
		this.targetType = targetType.name();
		this.targetId = targetId;
		this.content = content;
		this.projectId = projectId;
		this.projectStepId = projectStepId;
		this.actionTime = actionTime;
	}
}
