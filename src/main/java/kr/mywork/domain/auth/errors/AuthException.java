package kr.mywork.domain.auth.errors;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
	private final AuthErrorType errorType;

	public AuthException(AuthErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
