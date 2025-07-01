package kr.mywork.domain.member.errors;

public class MemberNotFoundException extends MemberException {
	public MemberNotFoundException(final MemberErrorType errorType) {
		super(errorType);
	}
}
