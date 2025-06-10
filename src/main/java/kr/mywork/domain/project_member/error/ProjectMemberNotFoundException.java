package kr.mywork.domain.project_member.error;

public class ProjectMemberNotFoundException extends ProjectMemberException {
	public ProjectMemberNotFoundException(final ProjectMemberErrorType errorType) {
		super(errorType);
	}
}
