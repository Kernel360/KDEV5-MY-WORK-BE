package kr.mywork.domain.post.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.notification.service.NotificationService;
import kr.mywork.domain.notification.service.RealTimeNotificationService;
import kr.mywork.domain.post.listener.event.PostApprovalNotificationEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostApprovalNotificationTxListener {

	private final RealTimeNotificationService realTimeNotificationService;
	private final NotificationService notificationService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Async("eventTaskExecutor")
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePostApprovalAlarmEvent(final PostApprovalNotificationEvent event) {
		final long unreadCount = notificationService.countUnreadNotifications(event.authorId());
		realTimeNotificationService.sendNotification(event.authorId(), "notification-unread-count", unreadCount);
		saveNotification(event);
	}

	private void saveNotification(final PostApprovalNotificationEvent event) {
		notificationService.save(
			event.authorId(), event.authorName(), event.postTitle(), event.actorName(), event.actorId(),
			event.targetType(), event.postId(), event.notificationActionType(), event.modifiedAt(), event.projectId(),
			event.projectStepId());
	}
}
