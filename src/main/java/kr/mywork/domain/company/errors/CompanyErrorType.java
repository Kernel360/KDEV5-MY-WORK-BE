package kr.mywork.domain.company.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyErrorType {
	ID_NOT_FOUND(CompanyErrorCode.ERROR_COMPANY01, "회사 아이디를 찾을 수 없습니다."),
	TYPE_NOT_FOUND(CompanyErrorCode.ERROR_COMPANY02, "회사 타입을 찾을 수 없습니다."),
	COMPANY_NOT_FOUND(CompanyErrorCode.ERROR_COMPANY03, "회사가 존재 하지 않습니다."),
	COMPANY_IMAGE_EXIST(CompanyErrorCode.ERROR_COMPANY04, "이미지 로고가 이미 존재합니다."),
	COMPANY_IMAGE_EMPTY(CompanyErrorCode.ERROR_COMPANY05, "이미지가 존재하지 않습니다."),
	COMPANY_BUSINESS_NUMBER_EXIST(CompanyErrorCode.ERROR_COMPANY06, "사업자 번호를 가진 회사가 존재합니다.");

	private final CompanyErrorCode errorCode;
	private final String message;
}
