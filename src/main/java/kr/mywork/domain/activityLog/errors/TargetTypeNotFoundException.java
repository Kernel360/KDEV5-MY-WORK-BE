package kr.mywork.domain.activityLog.errors;

public class TargetTypeNotFoundException extends ActivityLogException {
	public TargetTypeNotFoundException(final ActivityLogErrorType errorType) {
		super(errorType);
	}
}
