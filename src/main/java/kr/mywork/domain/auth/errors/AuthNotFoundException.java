package kr.mywork.domain.auth.errors;

public class AuthNotFoundException extends AuthException {
	public AuthNotFoundException(final AuthErrorType errorType) {
		super(errorType);
	}
}
