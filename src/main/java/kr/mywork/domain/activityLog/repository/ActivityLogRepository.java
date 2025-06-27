package kr.mywork.domain.activityLog.repository;

import java.util.List;

import kr.mywork.domain.activityLog.model.ActionType;
import kr.mywork.domain.activityLog.model.ActivityLog;
import kr.mywork.domain.activityLog.model.TargetType;
import kr.mywork.domain.activityLog.service.dto.response.ActivityLogSelectResponse;

public interface ActivityLogRepository {
	ActivityLog save(ActivityLog activityLog);

	List<ActivityLogSelectResponse> findActivityLogBySearchConditionWithPaging(
		int page, int activityLogPageSize, String companyType, TargetType targetType, ActionType actionType);

	Long countTotalActivityLogByCondition(
		String companyType, TargetType targetType, ActionType actionType);
}
