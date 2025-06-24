package kr.mywork.domain.activityLog.errors;

public class FieldTypeNotFoundException extends ActivityLogException {
	public FieldTypeNotFoundException(final ActivityLogErrorType errorType) {
		super(errorType);
	}
}
