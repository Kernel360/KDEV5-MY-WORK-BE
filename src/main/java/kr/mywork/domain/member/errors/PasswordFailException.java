package kr.mywork.domain.member.errors;

public class PasswordFailException extends MemberException {
	public PasswordFailException(final MemberErrorType errorType) {
		super(errorType);
	}
}
