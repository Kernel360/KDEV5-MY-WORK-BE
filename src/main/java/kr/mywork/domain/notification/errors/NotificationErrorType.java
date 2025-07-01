package kr.mywork.domain.notification.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationErrorType {
	NOTIFICATION_NOT_FOUND(NotificationErrorCode.ERROR_NOTIFICATION_TYPE01, "존재하지 않는 알림입니다.");

	private final NotificationErrorCode errorCode;
	private final String message;
}
