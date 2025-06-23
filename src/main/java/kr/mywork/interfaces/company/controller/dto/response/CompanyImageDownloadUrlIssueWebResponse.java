package kr.mywork.interfaces.company.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.company.service.dto.response.CompanyImageDownloadUrlIssueResponse;

public record CompanyImageDownloadUrlIssueWebResponse(UUID companyId, String downloadUrl) {

	public static CompanyImageDownloadUrlIssueWebResponse fromServiceDto(
		final CompanyImageDownloadUrlIssueResponse companyImageDownloadUrlIssueResponse) {
		return new CompanyImageDownloadUrlIssueWebResponse(
			companyImageDownloadUrlIssueResponse.companyId(),
			companyImageDownloadUrlIssueResponse.downloadUrl());
	}
}
