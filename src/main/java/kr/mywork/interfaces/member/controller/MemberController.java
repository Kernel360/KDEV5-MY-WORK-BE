package kr.mywork.interfaces.member.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.member.service.MemberService;
import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;
import kr.mywork.interfaces.member.controller.dto.response.CompanyMemberWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Validated
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/company/{companyId}")
	public ApiResponse<CompanyMemberWebResponse> getCompanyMember(
		@PathVariable(name = "companyId") UUID companyId,
		@RequestParam(defaultValue = "1")@Min(value = 1, message = "{invalid.page-size}") int page
	){
		List<CompanyMemberResponse> companyMemberResponse = memberService.findMemberByCompanyId(companyId,page);

		long total = memberService.countMembersByCompanyId(companyId);

		CompanyMemberWebResponse companyMemberWebResponse = CompanyMemberWebResponse.from(companyMemberResponse,total);

		return ApiResponse.success(companyMemberWebResponse);
	}


}
