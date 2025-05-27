package kr.mywork.interfaces.company.controller;

import java.util.UUID;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.company.service.CompanyService;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.domain.company.service.dto.request.CompanyUpdateRequest;
import kr.mywork.domain.company.service.dto.response.CompanyDetailResponse;
import kr.mywork.interfaces.company.controller.dto.request.CompanyCreateWebRequest;
import kr.mywork.interfaces.company.controller.dto.request.CompanyDeleteWebRequest;
import kr.mywork.interfaces.company.controller.dto.request.CompanyDetailWebRequest;
import kr.mywork.interfaces.company.controller.dto.request.CompanyUpdateWebRequest;
import kr.mywork.interfaces.company.controller.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

	@PutMapping()
	public ApiResponse<CompanyUpdateWebResponse> updateCompany(
			@RequestBody final CompanyUpdateWebRequest companyUpdateWebRequest) {

		final CompanyUpdateRequest companyUpdateRequest = companyUpdateWebRequest.toServiceDto();

		final UUID companyId = companyService.updateCompany(companyUpdateRequest);

		final CompanyUpdateWebResponse companyUpdateWebResponse = new CompanyUpdateWebResponse(companyId);

		return ApiResponse.success(companyUpdateWebResponse);
	}

	@DeleteMapping
	public ApiResponse<CompanyDeleteWebResponse> deleteCompany(
			@RequestBody final CompanyDeleteWebRequest companyDeleteWebRequest
			){
		final UUID deleteCompanyId = companyService.deleteCompany(companyDeleteWebRequest.id());

		final CompanyDeleteWebResponse companyDeleteWebResponse = new CompanyDeleteWebResponse(deleteCompanyId);

		return ApiResponse.success(companyDeleteWebResponse);
	}
	@GetMapping("/{companyId}")
	public ApiResponse<CompanyDetailWebResponse> companyDetail(
			@PathVariable final UUID companyId
	) {
		final CompanyDetailResponse companyDetailResponse = companyService.searchCompanyDetail(companyId);

		final CompanyDetailWebResponse companyDetailWebResponse = new CompanyDetailWebResponse(companyDetailResponse);

		return ApiResponse.success(companyDetailWebResponse);
	}
}
