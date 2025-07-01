package kr.mywork.infrastructure.activity_log.rdb;

import static kr.mywork.domain.activityLog.model.QActivityLog.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.transaction.Transactional;
import kr.mywork.domain.activityLog.model.ActionType;
import kr.mywork.domain.activityLog.model.ActivityLog;
import kr.mywork.domain.activityLog.model.TargetType;
import kr.mywork.domain.activityLog.repository.ActivityLogRepository;
import kr.mywork.domain.activityLog.service.dto.response.ActivityLogSelectResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class QueryDslActivityLogRepository implements ActivityLogRepository {

	private final JpaActivityLogRepository jpaActivityLogRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public ActivityLog save(ActivityLog activityLog) {
		return jpaActivityLogRepository.save(activityLog);
	}

	@Override
	public List<ActivityLogSelectResponse> findActivityLogBySearchConditionWithPaging(
		int page, int activityLogPageSize, String companyType, TargetType targetType, ActionType actionType) {

			final int offset = (page - 1) * activityLogPageSize;

			return queryFactory.select(Projections.constructor(ActivityLogSelectResponse.class,
					activityLog.id,
					activityLog.actionTime,
					activityLog.actionType,
					activityLog.targetType,
					activityLog.actorName,
					activityLog.actorCompanyName
				))
				.from(activityLog)
				.where(
					eqCompanyType(companyType),
					eqTargetType(targetType),
					eqActionType(actionType))
				.orderBy(activityLog.actionTime.desc())
				.offset(offset)
				.limit(activityLogPageSize)
				.fetch();
	}

	public Long countTotalActivityLogByCondition(
		String companyType, TargetType targetType, ActionType actionType) {

		return queryFactory.select(activityLog.id.count())
			.from(activityLog)
			.where(
				eqCompanyType(companyType),
				eqTargetType(targetType),
				eqActionType(actionType))
			.fetchOne();

	}

	private BooleanExpression eqCompanyType(String companyType) {
		if (companyType == null) {
			return null;
		}

		return activityLog.companyType.stringValue().eq(companyType);
	}

	private BooleanExpression eqTargetType(TargetType targetType) {
		if (targetType == null) {
			return null;
		}

		return activityLog.targetType.eq(targetType);
	}

	private BooleanExpression eqActionType(ActionType actionType) {
		if (actionType == null) {
			return null;
		}

		return activityLog.actionType.eq(actionType);
	}

}
