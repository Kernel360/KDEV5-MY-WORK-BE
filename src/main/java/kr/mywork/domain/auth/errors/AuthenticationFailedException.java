package kr.mywork.domain.auth.errors;

public class AuthenticationFailedException extends AuthException {
	public AuthenticationFailedException(AuthErrorType errorType) {
		super(errorType);
	}
}
