package kr.mywork.domain.auth.errors;

public class InvalidTokenException extends AuthException {
	public InvalidTokenException(AuthErrorType errorType) {
		super(errorType);
	}
}
