package kr.mywork.domain.notification.errors;

public class NotificationEmitterNotFoundException extends NotificationException {
	public NotificationEmitterNotFoundException(final NotificationErrorType errorType) {
		super(errorType);
	}
}
