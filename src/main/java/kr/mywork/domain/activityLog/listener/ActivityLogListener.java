package kr.mywork.domain.activityLog.listener;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.activityLog.listener.eventObject.CreateEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.DeleteEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.ModifyEventObject;
import kr.mywork.domain.activityLog.model.ActivityLog;
import kr.mywork.domain.activityLog.model.LogDetail;
import kr.mywork.domain.activityLog.repository.ActivityLogRepository;
import kr.mywork.domain.activityLog.service.ActivityLogService;
import kr.mywork.domain.activityLog.service.LogDetailService;
import kr.mywork.domain.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ActivityLogListener {

	private final ActivityLogRepository activityLogRepository;
	private final CompanyRepository companyRepository;
	private final LogDetailService logDetailService;
	private final ActivityLogService activityLogService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleProjectCreated(CreateEventObject event) {

		ActivityLog activityLog = activityLogService.makeCreatedActivityLog(event);
		activityLogRepository.save(activityLog);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleProjectModified(ModifyEventObject event) {

		ActivityLog activityLog = activityLogService.makeModifiedActivityLog(event);
		ActivityLog savedLog = activityLogRepository.save(activityLog);

		List<LogDetail> diffValue = logDetailService.makeLogDetails(event, savedLog.getId());

		logDetailService.saveAll(diffValue);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleProjectDeleted(DeleteEventObject event) {

		ActivityLog activityLog = activityLogService.makeDeletedActivityLog(event);
		activityLogRepository.save(activityLog);
	}
}
