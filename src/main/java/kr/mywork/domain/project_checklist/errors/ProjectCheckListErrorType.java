package kr.mywork.domain.project_checklist.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProjectCheckListErrorType {
	PROJECT_CHECK_LIST_NOT_FOUND(ProjectCheckListErrorCode.ERROR_PROJECT_CHECK_LIST01, "체크리스트를 찾을 수 없습니다.");

	private final ProjectCheckListErrorCode errorCode;
	private final String message;
}
