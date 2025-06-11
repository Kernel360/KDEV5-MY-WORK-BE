package kr.mywork.domain.project_step.errors;

import lombok.Getter;

@Getter
public abstract class ProjectStepException extends RuntimeException {
	private final ProjectStepErrorType errorType;

	public ProjectStepException(final ProjectStepErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
