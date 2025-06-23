package kr.mywork.infrastructure.activity_log.rdb;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.activityLog.model.LogDetail;

public interface JpaLogDetailRepository extends JpaRepository<LogDetail, UUID> {
	List<LogDetail> findByActivityLogId(UUID activityLogId);
}
