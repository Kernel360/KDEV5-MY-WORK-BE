package kr.mywork.domain.auth.errors;

public class JwtAccessDeniedException extends AuthException {
	public JwtAccessDeniedException(AuthErrorType errorType) {
		super(errorType);
	}
}
