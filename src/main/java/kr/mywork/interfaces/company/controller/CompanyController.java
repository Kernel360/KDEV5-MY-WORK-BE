package kr.mywork.interfaces.company.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
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
import kr.mywork.interfaces.company.controller.dto.request.CompanyUpdateWebRequest;
import kr.mywork.interfaces.company.controller.dto.response.*;
import kr.mywork.interfaces.member.controller.dto.response.CompanyMemberListWebResponse;
import kr.mywork.interfaces.member.controller.dto.response.CompanyMemberWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@Validated
public class CompanyController {

	public static final String COMPANY_TYPE_REGX = "^(DEV|CLIENT)$";
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
		@RequestBody final CompanyCreateWebRequest companyCreateWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {

		final CompanyCreateRequest companyCreateRequest = companyCreateWebRequest.toServiceDto();

		final UUID createdCompanyId = companyService.createCompany(companyCreateRequest, loginMemberDetail);

		final CompanyCreateWebResponse companyCreateWebResponse = new CompanyCreateWebResponse(createdCompanyId);

		return ApiResponse.success(companyCreateWebResponse);
	}

	@PutMapping
	public ApiResponse<CompanyUpdateWebResponse> updateCompany(
		@RequestBody final CompanyUpdateWebRequest companyUpdateWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {

		final CompanyUpdateRequest companyUpdateRequest = companyUpdateWebRequest.toServiceDto();

		final UUID companyId = companyService.updateCompany(companyUpdateRequest, loginMemberDetail);

		final CompanyUpdateWebResponse companyUpdateWebResponse = new CompanyUpdateWebResponse(companyId);

		return ApiResponse.success(companyUpdateWebResponse);
	}

	@DeleteMapping("/{companyId}")
	public ApiResponse<CompanyDeleteWebResponse> deleteCompany(
		@PathVariable (name="companyId")UUID companyId,
		@LoginMember LoginMemberDetail loginMemberDetail) {
		final UUID deleteCompanyId = companyService.deleteCompany(companyId, loginMemberDetail);

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
		@RequestParam(name = "companyType", required = false) @Pattern(regexp = COMPANY_TYPE_REGX, message = "{invalid.company-type}") final String companyType,
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
		@RequestParam(name = "companyType",required = false) @Pattern(regexp = COMPANY_TYPE_REGX, message = "{invalid.company-type}") final String companyType) {
		final List<CompanyNameResponse> companyNames = companyService.findCompanyNamesByCompanyType(companyType);

		final List<CompanyNameWebResponse> companyNameWebResponses = companyNames.stream()
				.map(CompanyNameWebResponse::from)
				.toList();

		return ApiResponse.success(new CompanyNamesWebResponse(companyNameWebResponses));
	}

	@GetMapping("/{companyId}/members")
	public ApiResponse<CompanyMemberWebResponse> getCompanyMember(
			@PathVariable(name = "companyId") UUID companyId,
			@RequestParam(defaultValue = "1") @Min(value = 1, message = "{invalid.page-size}") int page,
			@RequestParam(name = "memberName", required = false) final String memberName
	) {
		List<CompanyMemberResponse> companyMemberResponses = memberService.findMemberByCompanyId(companyId, page,memberName);

		long total = memberService.countMembersByCompanyId(companyId,memberName);

		List<CompanyMemberListWebResponse> companyMemberWebResponses = companyMemberResponses.stream()
			.map(CompanyMemberListWebResponse::fromService)
			.toList();

		return ApiResponse.success(new CompanyMemberWebResponse(total, companyMemberWebResponses));
	}
}
