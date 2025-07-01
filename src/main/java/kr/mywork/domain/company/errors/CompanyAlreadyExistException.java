package kr.mywork.domain.company.errors;

public class CompanyAlreadyExistException extends CompanyException {
	public CompanyAlreadyExistException(final CompanyErrorType companyErrorType) {
		super(companyErrorType);
	}
}
