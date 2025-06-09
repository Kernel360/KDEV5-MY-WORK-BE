package kr.mywork.domain.auth.errors;

public class InvalidTokenException extends AuthException {
	public InvalidTokenException(final AuthErrorType authErrorType) {
		super(authErrorType);
	}
}
