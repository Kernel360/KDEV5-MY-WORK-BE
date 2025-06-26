package kr.mywork.interfaces.notification.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.notification.service.dto.response.NotificationSelectResponse;

public record NotificationSelectWebResponse (UUID id, UUID actorId, String actorName, String actionType,
											 String targetType, UUID targetId, String content, UUID projectId, UUID projectStepId,
											 @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime actionTime) {

	public static NotificationSelectWebResponse from(NotificationSelectResponse response) {
		return new NotificationSelectWebResponse(
			response.getId(),
			response.getActorId(),
			response.getActorName(),
			response.getActionType(),
			response.getTargetType(),
			response.getTargetId(),
			response.getContent(),
			response.getProjectId(),
			response.getProjectStepId(),
			response.getActionTime()
		);
	}
}
