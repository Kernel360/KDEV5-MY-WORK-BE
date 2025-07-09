package kr.mywork.domain.notification.listener.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.notification.service.NotificationService;
import kr.mywork.domain.notification.service.RealTimeNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationTxEventListener {

	private final RealTimeNotificationService realTimeNotificationService;
	private final NotificationService notificationService;

	@Async(value = "eventTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleNotificationCreateEvent(final NotificationCreateEvent event) {
		long unreadCount = notificationService.countUnreadNotifications(event.authorId());
		realTimeNotificationService.sendNotification(event.authorId(), "notification-unread-count", unreadCount);
		saveNotification(event);
	}

	private void saveNotification(final NotificationCreateEvent event) {
		notificationService.save(
			event.authorId(),
			event.authorName(),
			event.content(),
			event.actorName(),
			event.actorId(),
			event.targetType(),
			event.targetId(),
			event.notificationActionType(),
			event.modifiedAt(),
			event.projectId(),
			event.projectStepId()
		);
	}
}
