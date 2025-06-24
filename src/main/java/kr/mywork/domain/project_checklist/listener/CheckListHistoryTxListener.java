package kr.mywork.domain.project_checklist.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.project_checklist.listener.event.CheckListApprovalUpdateEvent;
import kr.mywork.domain.project_checklist.listener.event.CheckListHistoryCreationEvent;
import kr.mywork.domain.project_checklist.model.CheckListHistory;
import kr.mywork.domain.project_checklist.repository.CheckListHistoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckListHistoryTxListener {

	private final CheckListHistoryRepository checkListHistoryRepository;

	@Async("eventTaskExecutor")
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void handleCheckListCreatedHistory(final CheckListHistoryCreationEvent event) {
		final CheckListHistory checkListHistory = CheckListHistory.CreationHistory(event.checkListId(),
			event.companyName(), event.authorName(), event.approval());

		checkListHistoryRepository.save(checkListHistory);
	}

	@Async("eventTaskExecutor")
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void handleCheckListApprovalHistory(final CheckListApprovalUpdateEvent event) {

		final CheckListHistory checkListHistory = CheckListHistory.updateHistory(event.checkListId(),
			event.companyName(), event.authorName(), event.approval(), event.reason());

		checkListHistoryRepository.save(checkListHistory);
	}
}
