package kr.mywork.domain.project_checklist.errors;

public class ProjectCheckListNotFoundException extends ProjectCheckListException {
	public ProjectCheckListNotFoundException(ProjectCheckListErrorType errorType) {
		super(errorType);
	}
}
