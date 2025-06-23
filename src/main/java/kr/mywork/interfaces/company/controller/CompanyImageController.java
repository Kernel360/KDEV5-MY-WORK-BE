package kr.mywork.interfaces.company.controller;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.company.service.CompanyImageService;
import kr.mywork.domain.company.service.dto.response.CompanyImageDeleteResponse;
import kr.mywork.domain.company.service.dto.response.CompanyImageDownloadUrlIssueResponse;
import kr.mywork.domain.company.service.dto.response.CompanyImageUploadUrlIssueResponse;
import kr.mywork.interfaces.company.controller.dto.request.CompanyImageUploadUrlIssueWebRequest;
import kr.mywork.interfaces.company.controller.dto.response.CompanyImageDeleteWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanyImageDownloadUrlIssueWebResponse;
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

	@GetMapping("/images/download-url")
	public ApiResponse<CompanyImageDownloadUrlIssueWebResponse> downloadImageUrl(
		@RequestParam("companyId") UUID companyId) {

		final CompanyImageDownloadUrlIssueResponse companyImageDownloadUrlIssueResponse =
			companyImageService.downloadCompanyImage(companyId);

		return ApiResponse.success(
			CompanyImageDownloadUrlIssueWebResponse.fromServiceDto(companyImageDownloadUrlIssueResponse));
	}

	@DeleteMapping("/images/{companyId}")
	public ApiResponse<CompanyImageDeleteWebResponse> deleteImage(
		@PathVariable("companyId") UUID companyId) {

		final CompanyImageDeleteResponse companyImageDeleteResponse = companyImageService.deleteImage(companyId);

		return ApiResponse.success(CompanyImageDeleteWebResponse.fromServiceDto(companyImageDeleteResponse));
	}
}
