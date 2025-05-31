package kr.mywork.domain.member.errors;

public class MemberIdNotFoundException extends MemberException {
	public MemberIdNotFoundException(final MemberErrorType errorType) {
		super(errorType);
	}
}
