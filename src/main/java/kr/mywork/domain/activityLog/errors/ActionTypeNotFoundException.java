package kr.mywork.domain.activityLog.errors;

public class ActionTypeNotFoundException extends ActivityLogException {
	public ActionTypeNotFoundException(final ActivityLogErrorType errorType) {
		super(errorType);
	}
}
