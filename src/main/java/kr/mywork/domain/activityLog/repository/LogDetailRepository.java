package kr.mywork.domain.activityLog.repository;

import java.util.List;
import java.util.UUID;

import kr.mywork.domain.activityLog.model.LogDetail;

public interface LogDetailRepository {
	void saveAll(List<LogDetail> diffValue);

	List<LogDetail> findByActivityLogId(UUID activityLogId);
}
