package kr.mywork.domain.company.errors;

public class CompanyTypeNotFoundException extends CompanyException {
	public CompanyTypeNotFoundException(final CompanyErrorType errorType) {
		super(errorType);
	}
}
