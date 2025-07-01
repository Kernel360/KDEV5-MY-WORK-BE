package kr.mywork.domain.company.errors;

public class CompanyImageAlreadyExistException extends CompanyException {
	public CompanyImageAlreadyExistException(final CompanyErrorType errorType) {
		super(errorType);
	}
}
