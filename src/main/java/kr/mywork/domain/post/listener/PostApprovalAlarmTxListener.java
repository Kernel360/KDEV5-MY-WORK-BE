package kr.mywork.domain.post.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.notification.service.NotificationService;
import kr.mywork.domain.post.listener.event.PostApprovalAlarmEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostApprovalAlarmTxListener {

	private final NotificationService notificationService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Async("eventTaskExecutor")
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePostApprovalAlarmEvent(final PostApprovalAlarmEvent event) {

		notificationService.save(
			event.authorId(), event.authorName(), event.postTitle(), event.memberName(), event.memberId(),
			event.targetType(), event.postId(), event.notificationActionType(), event.modifiedAt(), event.projectId(),
			event.projectStepId());
	}
}
