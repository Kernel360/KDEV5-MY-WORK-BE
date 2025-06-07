package kr.mywork.interfaces.company.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.company.service.dto.response.CompanyListOnlyIdNameResponse;

public record CompanyListContainIdNameResponse (UUID companyId,String companyName) {

	public static CompanyListContainIdNameResponse from(CompanyListOnlyIdNameResponse companyListOnlyIdNameResponse){
		return new CompanyListContainIdNameResponse(
			companyListOnlyIdNameResponse.id(),
			companyListOnlyIdNameResponse.name()
		);
	}
}
