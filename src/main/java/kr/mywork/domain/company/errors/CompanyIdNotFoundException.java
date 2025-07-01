package kr.mywork.domain.company.errors;

public class CompanyIdNotFoundException extends CompanyException {
	public CompanyIdNotFoundException(final CompanyErrorType errorType) {
		super(errorType);
	}
}
