package kr.mywork.domain.company.errors;

import lombok.Getter;

@Getter
public abstract class CompanyException extends RuntimeException {
	private final CompanyErrorType errorType;

	public CompanyException(final CompanyErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
