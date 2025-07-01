package kr.mywork.domain.member.errors;

public class MemberTypeNotFoundException extends MemberException {
	public MemberTypeNotFoundException(final MemberErrorType errorType) {
		super(errorType);
	}
}
