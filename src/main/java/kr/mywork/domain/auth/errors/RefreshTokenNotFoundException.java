package kr.mywork.domain.auth.errors;

public class RefreshTokenNotFoundException extends AuthException {
	public RefreshTokenNotFoundException(AuthErrorType errorType) {
		super(errorType);
	}
}
