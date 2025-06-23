package kr.mywork.interfaces.company.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.company.service.dto.response.CompanyImageDeleteResponse;

public record CompanyImageDeleteWebResponse(UUID companyId, boolean deleted) {

	public static CompanyImageDeleteWebResponse fromServiceDto(final CompanyImageDeleteResponse companyImageDeleteResponse) {
		return new CompanyImageDeleteWebResponse(
			companyImageDeleteResponse.companyId(), companyImageDeleteResponse.deleted());
	}
}
