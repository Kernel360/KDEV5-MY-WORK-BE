package kr.mywork.interfaces.company.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.company.service.dto.response.CompanyImageUploadUrlIssueResponse;

public record CompanyUploadUrlIssueWebResponse(UUID companyId, String uploadUrl) {

	public static CompanyUploadUrlIssueWebResponse fromServiceDto(
		final CompanyImageUploadUrlIssueResponse companyImageUploadUrlIssueResponse) {
		return new CompanyUploadUrlIssueWebResponse(
			companyImageUploadUrlIssueResponse.companyId(),
			companyImageUploadUrlIssueResponse.uploadUrl());
	}
}
