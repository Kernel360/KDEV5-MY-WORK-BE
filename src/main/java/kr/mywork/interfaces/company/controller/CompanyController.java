package kr.mywork.interfaces.company.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginUser;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.company.service.CompanyService;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.domain.company.service.dto.request.CompanyUpdateRequest;
import kr.mywork.domain.company.service.dto.response.CompanyDetailResponse;
import kr.mywork.domain.company.service.dto.response.CompanyNameResponse;
import kr.mywork.domain.company.service.dto.response.CompanySelectResponse;
import kr.mywork.domain.member.service.MemberService;
import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;
import kr.mywork.interfaces.company.controller.dto.request.CompanyCreateWebRequest;
import kr.mywork.interfaces.company.controller.dto.request.CompanyDeleteWebRequest;
import kr.mywork.interfaces.company.controller.dto.request.CompanyUpdateWebRequest;
import kr.mywork.interfaces.company.controller.dto.response.CompanyCreateWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanyDeleteWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanyDetailWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanyIdCreateWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanyNameWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanyNamesWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanyListWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanySelectWebResponse;
import kr.mywork.interfaces.company.controller.dto.response.CompanyUpdateWebResponse;
import kr.mywork.interfaces.member.controller.dto.response.CompanyMemberListWebResponse;
import kr.mywork.interfaces.member.controller.dto.response.CompanyMemberWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@Validated
public class CompanyController {

	private static final String COMPANY_TYPE_REGX = "^(DEV|CLIENT)$";
	private static final String COMPANY_KEYWORD_TYPE = "^(NAME|BUSINESS_NUMBER|PHONE_NUMBER|ADDRESS)$";

	private final CompanyService companyService;
	private final MemberService memberService;

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

	@PutMapping
	public ApiResponse<CompanyUpdateWebResponse> updateCompany(
		@RequestBody final CompanyUpdateWebRequest companyUpdateWebRequest) {

		final CompanyUpdateRequest companyUpdateRequest = companyUpdateWebRequest.toServiceDto();

		final UUID companyId = companyService.updateCompany(companyUpdateRequest);

		final CompanyUpdateWebResponse companyUpdateWebResponse = new CompanyUpdateWebResponse(companyId);

		return ApiResponse.success(companyUpdateWebResponse);
	}

	@DeleteMapping
	public ApiResponse<CompanyDeleteWebResponse> deleteCompany(
		@RequestBody final CompanyDeleteWebRequest companyDeleteWebRequest) {
		final UUID deleteCompanyId = companyService.deleteCompany(companyDeleteWebRequest.id());

		final CompanyDeleteWebResponse companyDeleteWebResponse = new CompanyDeleteWebResponse(deleteCompanyId);

		return ApiResponse.success(companyDeleteWebResponse);
	}

	@GetMapping("/{companyId}")
	public ApiResponse<CompanyDetailWebResponse> getCompanyDetail(@PathVariable(name = "companyId") UUID companyId) {
		CompanyDetailResponse companyDetailResponse = companyService.findCompanyById(companyId);

		CompanyDetailWebResponse companyDetailWebResponse = CompanyDetailWebResponse.from(companyDetailResponse);

		return ApiResponse.success(companyDetailWebResponse);
	}

	@GetMapping
	public ApiResponse<CompanyListWebResponse> findCompaniesByOffset(
		@RequestParam(name = "page") @Min(value = 1, message = "{invalid.page-size}") final int page,
		@RequestParam(name = "companyType") @Pattern(regexp = COMPANY_TYPE_REGX, message = "{invalid.company-type}") final String companyType,
		@RequestParam(name = "keyword", required = false) final String keyword,
		@RequestParam(name = "keywordType", required = false) @Pattern(regexp = COMPANY_KEYWORD_TYPE, message = "{invalid.company-search-type}") final String keywordType,
		@RequestParam(name = "deleted", required = false) final Boolean deleted
	) {
		final List<CompanySelectResponse> companySelectResponses =
			companyService.findCompaniesBySearchConditionWithPaging(page, companyType, keywordType, keyword, deleted);

		final Long totalCount = companyService.countTotalCompaniesByCondition(companyType, keywordType, keyword,
			deleted);

		List<CompanySelectWebResponse> companySelectWebResponses =
			companySelectResponses.stream().map(CompanySelectWebResponse::from).toList();

		return ApiResponse.success(new CompanyListWebResponse(companySelectWebResponses, totalCount));
	}

	@GetMapping("/names")
	public ApiResponse<CompanyNamesWebResponse> companyListOnlyIdNameWebResponseApiResponse(
		@RequestParam(name = "companyType") @Pattern(regexp = COMPANY_TYPE_REGX, message = "{invalid.company-type}") final String companyType) {
		final List<CompanyNameResponse> companyNames = companyService.findCompanyNamesByCompanyType(companyType);

		final List<CompanyNameWebResponse> companyNameWebResponses = companyNames.stream()
				.map(CompanyNameWebResponse::from)
				.toList();

		return ApiResponse.success(new CompanyNamesWebResponse(companyNameWebResponses));
	}

	@GetMapping("/{companyId}/members")
	public ApiResponse<CompanyMemberWebResponse> getCompanyMember(@PathVariable(name = "companyId") UUID companyId,
		@RequestParam(defaultValue = "1") @Min(value = 1, message = "{invalid.page-size}") int page) {
		List<CompanyMemberResponse> companyMemberResponses = memberService.findMemberByCompanyId(companyId, page);

		long total = memberService.countMembersByCompanyId(companyId);

		List<CompanyMemberListWebResponse> companyMemberWebResponses = companyMemberResponses.stream()
			.map(CompanyMemberListWebResponse::fromService)
			.toList();

		return ApiResponse.success(new CompanyMemberWebResponse(total, companyMemberWebResponses));
	}
}
