package kr.mywork.domain.notification.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	// 수신자 정보
	private UUID receiverMemberId;
	private String receiverMemberName;

	// 알림 내용
	private String notificationContent;

	// 액션자 정보
	private String actorName;
	private UUID actorId;

	// 타겟 정보
	@Enumerated(EnumType.STRING)
	private TargetType targetType;
	private UUID targetId;

	// 액션 정보
	@Enumerated(EnumType.STRING)
	private NotificationActionType actionType;
	private LocalDateTime actionDate;

	// 상태 관리
	private Boolean isRead;
	private Boolean isTargetDeleted;

	private UUID projectId;
	private UUID projectStepId;

	// 시간 정보
	@CreationTimestamp
	private LocalDateTime createdAt;

	public Notification(UUID receiverMemberId, String receiverMemberName,
		String notificationContent, String actorName, UUID actorId, TargetType targetType, UUID targetId,
		NotificationActionType actionType, LocalDateTime actionDate, UUID projectId, UUID projectStepId) {
		this.receiverMemberId = receiverMemberId;
		this.receiverMemberName = receiverMemberName;
		this.notificationContent = notificationContent;
		this.actorName = actorName;
		this.actorId = actorId;
		this.targetType = targetType;
		this.targetId = targetId;
		this.actionType = actionType;
		this.actionDate = actionDate;
		this.projectId = projectId;
		this.projectStepId = projectStepId;
		this.isRead = false;
		this.isTargetDeleted = false;
	}

	// 읽음 처리
	public void markAsRead() {
		this.isRead = true;
	}

	// 타겟 삭제 처리
	public void markTargetAsDeleted() {
		this.isTargetDeleted = true;
	}
}