package kr.mywork.interfaces.member.controller;

import java.util.List;
import java.util.UUID;

import kr.mywork.domain.member.service.dto.resquest.MemberCreateRequest;
import kr.mywork.interfaces.member.controller.dto.response.MemberCreateWebResponse;
import kr.mywork.interfaces.member.controller.dto.resquest.MemberCreateWebRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.member.service.MemberService;
import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;
import kr.mywork.interfaces.member.controller.dto.response.CompanyMemberWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/company/{companyId}")
	public ApiResponse<CompanyMemberWebResponse> getCompanyMember(
		@PathVariable(name = "companyId") UUID companyId,
		@RequestParam(defaultValue = "0") int page
	){
		Pageable pageable = PageRequest.of(page, 10);
		List<CompanyMemberResponse> companyMemberResponse = memberService.findMemberByCompanyId(companyId,pageable);

		long total = memberService.countMembersByCompanyId(companyId);

		CompanyMemberWebResponse companyMemberWebResponse = CompanyMemberWebResponse.from(companyMemberResponse,total);

		return ApiResponse.success(companyMemberWebResponse);
	}

	@PostMapping
	public ApiResponse<MemberCreateWebResponse> memberCreateWebResponseApiResponse(
			@RequestBody final MemberCreateWebRequest memberCreateWebRequest
	){

		final MemberCreateRequest memberCreateRequest = memberCreateWebRequest.toServiceDto();

		final UUID createdMemberId = memberService.createMember(memberCreateRequest);

		final MemberCreateWebResponse memberCreateWebResponse = new MemberCreateWebResponse(createdMemberId);

		return ApiResponse.success(memberCreateWebResponse);
	}

}
