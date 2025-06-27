package kr.mywork.domain.notification.errors;

import lombok.Getter;

@Getter
public abstract class NotificationException extends RuntimeException {
	private final NotificationErrorType errorType;

	public NotificationException(final NotificationErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
