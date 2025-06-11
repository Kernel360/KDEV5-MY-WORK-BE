package kr.mywork.domain.project_step.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProjectStepErrorType {
	PROJECT_STEP_NOT_FOUND(ProjectStepErrorCode.ERROR_PROJECT_STEP01, "프로젝트 단계를 찾을 수 없습니다.");

	private final ProjectStepErrorCode errorCode;
	private final String message;
}
