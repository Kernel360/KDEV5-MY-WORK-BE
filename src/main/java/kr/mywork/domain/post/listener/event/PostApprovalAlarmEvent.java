package kr.mywork.domain.post.listener.event;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.notification.model.NotificationActionType;
import kr.mywork.domain.notification.model.TargetType;

public record PostApprovalAlarmEvent(UUID authorId, String authorName, String postTitle, UUID memberId,
									 String memberName, TargetType targetType, UUID postId,
									 NotificationActionType notificationActionType, LocalDateTime modifiedAt,
									 UUID projectId, UUID projectStepId) {
}
