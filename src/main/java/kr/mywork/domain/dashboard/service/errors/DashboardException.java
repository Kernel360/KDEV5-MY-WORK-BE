package kr.mywork.domain.dashboard.service.errors;

import kr.mywork.domain.company.errors.CompanyErrorType;
import lombok.Getter;

@Getter
public class DashboardException extends RuntimeException {
	private final DashboardErrorType errorType;

	public DashboardException(final DashboardErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
