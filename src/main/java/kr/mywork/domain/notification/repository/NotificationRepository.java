package kr.mywork.domain.notification.repository;

import java.util.List;
import java.util.UUID;

import kr.mywork.domain.notification.model.Notification;
import kr.mywork.domain.notification.service.dto.response.NotificationSelectResponse;

public interface NotificationRepository {
	Notification save(Notification notification);

	List<NotificationSelectResponse> findByConditionWithPaging(int page, Boolean isRead, UUID memberId);

	Notification findById(UUID id);

	long countByMemberIdAndIsReadFalse(UUID memberId);
}
