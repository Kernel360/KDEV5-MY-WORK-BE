package kr.mywork.domain.dashboard.service.errors;

import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyException;

public class DashboardTypeNotFoundException extends DashboardException {
	public DashboardTypeNotFoundException(final DashboardErrorType errorType) {
		super(errorType);
	}
}
