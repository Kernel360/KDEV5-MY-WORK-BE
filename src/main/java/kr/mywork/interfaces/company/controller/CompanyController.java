package kr.mywork.interfaces.company.controller;

import java.util.UUID;

import kr.mywork.interfaces.company.controller.dto.request.CompanyDeleteWebRequest;
import kr.mywork.interfaces.company.controller.dto.response.CompanyDeleteWebResponse;
import org.springframework.web.bind.annotation.*;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.company.service.CompanyService;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.interfaces.company.controller.dto.response.CompanyCreateWebResponse;
import kr.mywork.interfaces.company.controller.dto.request.CompanyCreateWebRequest;
import kr.mywork.interfaces.company.controller.dto.response.CompanyIdCreateWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

	private final CompanyService companyService;

	@PostMapping("/id/generate")
	public ApiResponse<CompanyIdCreateWebResponse> createCompanyId() {
		final UUID companyId = companyService.createCompanyId();
		return ApiResponse.success(new CompanyIdCreateWebResponse(companyId));
	}

	@PostMapping
	public ApiResponse<CompanyCreateWebResponse> createCompany(
		@RequestBody final CompanyCreateWebRequest companyCreateWebRequest) {

		final CompanyCreateRequest companyCreateRequest = companyCreateWebRequest.toServiceDto();

		final UUID createdCompanyId = companyService.createCompany(companyCreateRequest);

		final CompanyCreateWebResponse companyCreateWebResponse = new CompanyCreateWebResponse(createdCompanyId);

		return ApiResponse.success(companyCreateWebResponse);
	}
	@DeleteMapping("delete/{id}")
	public ApiResponse<CompanyDeleteWebResponse> deleteCompany(
			@RequestBody final CompanyDeleteWebRequest companyDeleteWebRequest
			){
		final UUID deleteCompanyId = companyService.deleteCompany(companyDeleteWebRequest.companyId());
		final CompanyDeleteWebResponse companyDeleteWebResponse = new CompanyDeleteWebResponse(deleteCompanyId);
		return ApiResponse.success(companyDeleteWebResponse);
	}
}
