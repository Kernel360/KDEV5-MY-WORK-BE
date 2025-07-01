package kr.mywork.domain.activityLog.errors;

import lombok.Getter;

@Getter
public abstract class ActivityLogException extends RuntimeException {
	private final ActivityLogErrorType errorType;

	public ActivityLogException(final ActivityLogErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
