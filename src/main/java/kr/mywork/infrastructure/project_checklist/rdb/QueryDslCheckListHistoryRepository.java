package kr.mywork.infrastructure.project_checklist.rdb;

import org.springframework.stereotype.Repository;

import kr.mywork.domain.project_checklist.model.CheckListHistory;
import kr.mywork.domain.project_checklist.repository.CheckListHistoryRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslCheckListHistoryRepository implements CheckListHistoryRepository {

	private final JpaCheckListRepository jpaCheckListRepository;

	@Override
	public CheckListHistory save(final CheckListHistory checkListHistory) {
		return jpaCheckListRepository.save(checkListHistory);
	}
}
