package kr.mywork.domain.company.errors;

public class CompanyImageEmptyException extends CompanyException {
	public CompanyImageEmptyException(final CompanyErrorType errorType) {
		super(errorType);
	}
}
