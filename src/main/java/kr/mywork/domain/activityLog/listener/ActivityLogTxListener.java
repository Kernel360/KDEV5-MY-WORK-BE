package kr.mywork.domain.activityLog.listener;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogCreateEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogDeleteEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityModifyEvent;
import kr.mywork.domain.activityLog.model.ActivityLog;
import kr.mywork.domain.activityLog.model.LogDetail;
import kr.mywork.domain.activityLog.service.ActivityLogService;
import kr.mywork.domain.activityLog.service.LogDetailService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ActivityLogTxListener {

	private final LogDetailService logDetailService;
	private final ActivityLogService activityLogService;

	@Async(value = "eventTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleProjectCreated(final ActivityLogCreateEvent event) {

		ActivityLog activityLog = activityLogService.makeCreatedActivityLog(event);
		activityLogService.save(activityLog);
	}

	@Async(value = "eventTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleProjectModified(final ActivityModifyEvent event) {

		ActivityLog activityLog = activityLogService.makeModifiedActivityLog(event);
		ActivityLog savedLog = activityLogService.save(activityLog);

		List<LogDetail> diffValue = logDetailService.makeLogDetails(event, savedLog.getId());

		logDetailService.saveAll(diffValue);
	}

	@Async(value = "eventTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleProjectDeleted(final ActivityLogDeleteEvent event) {

		ActivityLog activityLog = activityLogService.makeDeletedActivityLog(event);
		activityLogService.save(activityLog);
	}
}
