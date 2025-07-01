package kr.mywork.domain.member.errors;

public class EmailAlreadyExistsException extends MemberException {
	public EmailAlreadyExistsException(final MemberErrorType errorType) {
		super(errorType);
	}
}
