package kr.mywork.domain.project_member.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProjectMemberErrorType {
	PROJECT_MEMBER_NOT_FOUND(ProjectMemberErrorCode.ERROR_PROJECT_MEMBER01, "프로젝트의 멤버를 찾을 수 없습니다.");

	private final ProjectMemberErrorCode errorCode;
	private final String message;
}
