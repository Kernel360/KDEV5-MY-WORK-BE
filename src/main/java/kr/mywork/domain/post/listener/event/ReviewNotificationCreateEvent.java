package kr.mywork.domain.post.listener.event;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.notification.model.NotificationActionType;
import kr.mywork.domain.notification.model.TargetType;

public record ReviewNotificationCreateEvent(UUID authorId, String authorName, String content, String actorName, UUID actorId,
											TargetType targetType, UUID targetId, NotificationActionType notificationActionType,
											LocalDateTime modifiedAt, UUID projectId, UUID projectStepId) {
}
