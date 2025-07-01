package kr.mywork.domain.auth.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorType {
	INVALID_ACCESS_TOKEN(AuthErrorCode.ERROR_AUTH01, "유효하지 않은 토큰입니다."),
	ACCESS_TOKEN_EXPIRED(AuthErrorCode.ERROR_AUTH02, "만료된 인증 토큰입니다."),
	REFRESH_TOKEN_NOT_FOUND(AuthErrorCode.ERROR_AUTH03, "Refresh Token이 존재하지 않습니다."),
	AUTHENTICATION_FAILED(AuthErrorCode.ERROR_AUTH04, "인증에 실패했습니다."),
	ACCESS_DENIED(AuthErrorCode.ERROR_AUTH05, "접근 권한이 없습니다."),
	INVALID_LOGIN(AuthErrorCode.ERROR_AUTH06, "이메일 또는 비밀번호가 올바르지 않습니다."),
	AUTHENTICATION_NOT_FOUND(AuthErrorCode.ERROR_AUTH07, "인증 정보를 조회할 수 없습니다."),
	INVALID_REFRESH_TOKEN(AuthErrorCode.ERROR_AUTH08, "리프레시 토큰이 유효하지 않습니다."),
	REFRESH_TOKEN_EXPIRED(AuthErrorCode.ERROR_AUTH09, "만료된 리프레시 토큰입니다."),
	INVALID_PASSWORD(AuthErrorCode.ERROR_AUTH010, "비밀번호가 일치하지 않습니다.");

	private final AuthErrorCode errorCode;
	private final String message;
}
