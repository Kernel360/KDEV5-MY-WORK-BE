package kr.mywork.domain.project_member.error;

import lombok.Getter;

@Getter
public abstract class ProjectMemberException extends RuntimeException {
	private final ProjectMemberErrorType errorType;

	public ProjectMemberException(final ProjectMemberErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
