package kr.mywork.interfaces.company.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.company.service.CompanyImageService;
import kr.mywork.domain.company.service.dto.response.CompanyImageUploadUrlIssueResponse;
import kr.mywork.interfaces.company.controller.dto.request.CompanyImageUploadUrlIssueWebRequest;
import kr.mywork.interfaces.company.controller.dto.response.CompanyUploadUrlIssueWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@Validated
public class CompanyImageController {

	private final CompanyImageService companyImageService;

	@PostMapping("/images/upload-url/issue")
	public ApiResponse<CompanyUploadUrlIssueWebResponse> issueImageUploadUrl(
		@RequestBody final CompanyImageUploadUrlIssueWebRequest companyImageUploadUrlIssueWebRequest) {

		final CompanyImageUploadUrlIssueResponse companyImageUploadUrlIssueResponse = companyImageService.issueCompanyImageUploadUrl(
			companyImageUploadUrlIssueWebRequest.getCompanyId(),
			companyImageUploadUrlIssueWebRequest.getFileName());

		return ApiResponse.success(CompanyUploadUrlIssueWebResponse.fromServiceDto(companyImageUploadUrlIssueResponse));
	}
}
