package kr.mywork.domain.project_checklist.listener.event;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.notification.model.NotificationActionType;
import kr.mywork.domain.notification.model.TargetType;

public record CheckListApprovalNotificationEvent(UUID authorId, String authorName, String checkListTitle,
												 String memberName, UUID memberId, TargetType targetType,
												 UUID checkListId, NotificationActionType notificationActionType,
												 LocalDateTime modifiedAt, UUID projectId, UUID projectStepId) {

}
