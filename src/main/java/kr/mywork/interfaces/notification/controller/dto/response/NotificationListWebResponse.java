package kr.mywork.interfaces.notification.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public record NotificationListWebResponse (UUID actorId, String actorName, String actionType,
										   String targetType, UUID targetId, String content, UUID projectId, UUID projectStepId,
										   @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime actionTime) {
}
