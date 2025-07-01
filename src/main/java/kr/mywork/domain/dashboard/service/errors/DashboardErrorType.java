package kr.mywork.domain.dashboard.service.errors;

import kr.mywork.domain.company.errors.CompanyErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DashboardErrorType {

	TYPE_NOT_FOUND(DashboardErrorCode.ERROR_DASHBOARD01, "대시보드의 타입을 확인할 수 없습니다.");

	private final DashboardErrorCode errorCode;
	private final String message;
}
