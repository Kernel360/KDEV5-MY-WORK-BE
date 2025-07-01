package kr.mywork.interfaces.company.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.company.service.dto.response.CompanyDetailResponse;

/**
 * 회사 상세 정보 + 소속 멤버 리스트를 포함한 웹 응답 DTO
 */
public record CompanyDetailWebResponse(
	UUID companyId,
	String name,
	String detail,
	String businessNumber,
	String address,
	String type,
	String contactPhoneNumber,
	String contactEmail,
	String logoImagePath
) {
	public static CompanyDetailWebResponse from(
		CompanyDetailResponse company) {

		return new CompanyDetailWebResponse(
			company.id(),
			company.name(),
			company.detail(),
			company.businessNumber(),
			company.address(),
			company.type(),
			company.contactPhoneNumber(),
			company.contactEmail(),
			company.logoImagePath()
		);
	}
}
