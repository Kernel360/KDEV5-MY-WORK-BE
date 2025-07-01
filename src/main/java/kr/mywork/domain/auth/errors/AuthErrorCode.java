package kr.mywork.domain.auth.errors;

public enum AuthErrorCode {
	ERROR_AUTH01, // 유효하지 않은 토큰
	ERROR_AUTH02, // 만료된 토큰
	ERROR_AUTH03, // Refresh Token 없음
	ERROR_AUTH04, // 인증 실패
	ERROR_AUTH05, // 접근 권한 없음
	ERROR_AUTH06,
	ERROR_AUTH07,
	ERROR_AUTH08,
	ERROR_AUTH09,
	ERROR_AUTH010;
}
