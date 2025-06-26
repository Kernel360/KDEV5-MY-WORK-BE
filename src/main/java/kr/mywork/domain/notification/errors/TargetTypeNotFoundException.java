package kr.mywork.domain.notification.errors;

import kr.mywork.domain.activityLog.errors.ActivityLogErrorType;
import kr.mywork.domain.activityLog.errors.ActivityLogException;

public class TargetTypeNotFoundException extends NotificationException {
	public TargetTypeNotFoundException(final NotificationErrorType errorType) {
		super(errorType);
	}
}
