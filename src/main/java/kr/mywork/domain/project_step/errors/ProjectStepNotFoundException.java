package kr.mywork.domain.project_step.errors;

public class ProjectStepNotFoundException extends ProjectStepException {
	public ProjectStepNotFoundException(ProjectStepErrorType errorType) {
		super(errorType);
	}
}
