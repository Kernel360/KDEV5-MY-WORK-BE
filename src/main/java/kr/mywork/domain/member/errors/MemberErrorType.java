package kr.mywork.domain.member.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorType {
	TYPE_NOT_FOUND(MemberErrorCode.ERROR_MEMBER01, "멤버 타입을 찾을 수 없습니다."),
	EMAIL_ALREADY_EXISTS(MemberErrorCode.ERROR_MEMBER02,"이미 가입된 이메일 주소가 있습니다."),
	ID_NOT_FOUND(MemberErrorCode.ERROR_MEMBER03,"멤버 ID를 찾을 수 없습니다."),
	MEMBER_NOT_FOUND(MemberErrorCode.ERROR_MEMBER04,"멤버를 찾을 수 없습니다."),
	WRONG_PASSWORD(MemberErrorCode.ERROR_MEMBER05,"멤버의 비밀번호가 일치하지 않습니다.");

	private final MemberErrorCode errorCode;
	private final String message;
}
