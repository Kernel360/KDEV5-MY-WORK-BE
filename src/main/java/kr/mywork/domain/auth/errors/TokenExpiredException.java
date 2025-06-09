package kr.mywork.domain.auth.errors;

public class TokenExpiredException extends AuthException {
	public TokenExpiredException(final AuthErrorType authErrorType) {
		super(authErrorType);
	}
}
