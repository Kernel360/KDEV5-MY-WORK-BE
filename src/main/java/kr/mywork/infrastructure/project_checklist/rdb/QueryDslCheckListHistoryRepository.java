package kr.mywork.infrastructure.project_checklist.rdb;

import static kr.mywork.domain.project_checklist.model.QCheckListHistory.checkListHistory;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.project_checklist.model.CheckListHistory;
import kr.mywork.domain.project_checklist.repository.CheckListHistoryRepository;
import kr.mywork.domain.project_checklist.service.dto.response.CheckListHistorySelectResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslCheckListHistoryRepository implements CheckListHistoryRepository {

	private final JpaCheckListRepository jpaCheckListRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public CheckListHistory save(final CheckListHistory checkListHistory) {
		return jpaCheckListRepository.save(checkListHistory);
	}

	@Override
	public List<CheckListHistorySelectResponse> findAllByCheckListId(final UUID checkListId) {
		return queryFactory.select(Projections.constructor(CheckListHistorySelectResponse.class,
				checkListHistory.id,
				checkListHistory.companyName,
				checkListHistory.authorName,
				checkListHistory.content,
				checkListHistory.approval,
				checkListHistory.createdAt))
			.from(checkListHistory)
			.where(checkListHistory.checkListId.eq(checkListId))
			.orderBy(checkListHistory.id.desc())
			.fetch();
	}
}
