package kr.mywork.domain.notification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.Min;
import kr.mywork.domain.notification.model.Notification;
import kr.mywork.domain.notification.model.NotificationActionType;
import kr.mywork.domain.notification.model.TargetType;
import kr.mywork.domain.notification.repository.NotificationRepository;
import kr.mywork.domain.notification.service.dto.request.NotificationReadRequest;
import kr.mywork.domain.notification.service.dto.response.NotificationReadResponse;
import kr.mywork.domain.notification.service.dto.response.NotificationSelectResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

	private final NotificationRepository notificationRepository;

	public void save(UUID authorId, String authorName, String content, String actorName,
		UUID actorId, TargetType targetType, UUID targetId, NotificationActionType notificationActionType, LocalDateTime modifiedAt, UUID projectId, UUID projectStepId) {

		notificationRepository.save(new Notification(authorId, authorName, content, actorName, actorId, targetType, targetId, notificationActionType, modifiedAt, projectId, projectStepId));
	}

	public List<NotificationSelectResponse> findByConditionWithPaging(int page, Boolean isRead, UUID memberId) {

		return notificationRepository.findByConditionWithPaging(page, isRead, memberId);
	}

	public NotificationReadResponse readNotification(NotificationReadRequest notificationReadRequest) {
		Notification notification = notificationRepository.findById(notificationReadRequest.getId());

		notification.markAsRead();
		return new NotificationReadResponse(notification.getId());
	}

	public long countUnreadNotifications(UUID memberId) {
		return notificationRepository.countByMemberIdAndIsReadFalse(memberId);
	}

}
