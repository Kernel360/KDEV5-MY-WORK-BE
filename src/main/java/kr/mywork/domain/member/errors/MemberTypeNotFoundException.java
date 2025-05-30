package kr.mywork.domain.member.errors;

import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyException;

public class MemberTypeNotFoundException extends MemberException {
	public MemberTypeNotFoundException(final MemberErrorType errorType) {
		super(errorType);
	}
}
