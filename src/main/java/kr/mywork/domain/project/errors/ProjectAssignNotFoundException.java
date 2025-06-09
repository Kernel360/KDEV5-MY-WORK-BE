package kr.mywork.domain.project.errors;

public class ProjectAssignNotFoundException extends ProjectException {
	public ProjectAssignNotFoundException(final ProjectErrorType errorType) {
		super(errorType);
	}
}
