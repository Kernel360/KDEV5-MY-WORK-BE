package kr.mywork.domain.project_checklist.errors;

import lombok.Getter;

@Getter
public abstract class ProjectCheckListException extends RuntimeException {
	private final ProjectCheckListErrorType errorType;

	public ProjectCheckListException(final ProjectCheckListErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
