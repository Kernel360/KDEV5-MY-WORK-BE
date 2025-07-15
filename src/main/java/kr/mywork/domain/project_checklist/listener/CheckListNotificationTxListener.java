package kr.mywork.domain.project_checklist.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.notification.service.NotificationService;
import kr.mywork.domain.notification.service.RealTimeNotificationService;
import kr.mywork.domain.project_checklist.listener.event.CheckListApprovalNotificationEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckListNotificationTxListener {

	private final RealTimeNotificationService realTimeNotificationService;
	private final NotificationService notificationService;

	@Async("eventTaskExecutor")
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void handleCheckListCreatedHistory(final CheckListApprovalNotificationEvent event) {
		saveCheckListNotification(event);
		final long unreadCount = notificationService.countUnreadNotifications(event.authorId());
		realTimeNotificationService.sendNotification(event.authorId(), "notification-unread-count", unreadCount);
	}

	private void saveCheckListNotification(final CheckListApprovalNotificationEvent event) {
		notificationService.save(
			event.authorId(),
			event.authorName(),
			event.checkListTitle(),
			event.actorName(),
			event.actorId(),
			event.targetType(),
			event.checkListId(),
			event.notificationActionType(),
			event.modifiedAt(),
			event.projectId(),
			event.projectStepId());
	}
}
