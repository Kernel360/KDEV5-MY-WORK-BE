package kr.mywork.interfaces.company.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.company.service.dto.response.CompanyNameResponse;

public record CompanyNameWebResponse(UUID companyId, String companyName) {

	public static CompanyNameWebResponse from(
		CompanyNameResponse companyNameResponse){
		return new CompanyNameWebResponse(
			companyNameResponse.id(),
			companyNameResponse.name());
	}
}
