package kr.mywork.domain.company.model;

import java.util.Arrays;

import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyTypeNotFoundException;

public enum CompanyType {
	DEV, CLIENT, SYSTEM;

	public static CompanyType from(String value) {
		return Arrays.stream(CompanyType.values())
			.filter(companyType -> companyType.name().equals(value))
			.findAny()
			.orElseThrow(() -> new CompanyTypeNotFoundException(CompanyErrorType.TYPE_NOT_FOUND));
	}
}
