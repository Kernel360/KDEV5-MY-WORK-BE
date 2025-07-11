package kr.mywork.domain.post.listener.event;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.notification.model.NotificationActionType;
import kr.mywork.domain.notification.model.TargetType;

public record PostApprovalNotificationEvent(UUID authorId, String authorName, String postTitle, UUID actorId,
											String actorName, TargetType targetType, UUID postId,
											NotificationActionType notificationActionType, LocalDateTime modifiedAt,
											UUID projectId, UUID projectStepId) {
}
