package kr.mywork.infrastructure.activity_log.rdb;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.activityLog.model.ActivityLog;

public interface JpaActivityLogRepository extends JpaRepository<ActivityLog, UUID> {

}
