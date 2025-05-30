package kr.mywork.domain.member.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorType {
	TYPE_NOT_FOUND(MemberErrorCode.ERROR_COMPANY01, "멤버 타입을 찾을 수 없습니다.");


	private final MemberErrorCode errorCode;
	private final String message;
}
