package kr.mywork.interfaces.member.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.company.service.dto.response.MemberDetailResponse;
import kr.mywork.domain.member.model.MemberRole;
import kr.mywork.domain.member.service.MemberService;
import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;
import kr.mywork.domain.member.service.dto.request.MemberUpdateRequest;
import kr.mywork.domain.member.service.dto.response.MemberProjectInfoResponse;
import kr.mywork.domain.member.service.dto.response.MemberSelectResponse;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.interfaces.member.controller.dto.request.MemberCreateWebRequest;
import kr.mywork.interfaces.member.controller.dto.request.MemberDeleteWebRequest;
import kr.mywork.interfaces.member.controller.dto.request.MemberUpdateWebRequest;
import kr.mywork.interfaces.member.controller.dto.request.ResetPasswordWebRequest;
import kr.mywork.interfaces.member.controller.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Validated
public class MemberController {

    private static final String MEMBER_SEARCH_TYPE = "^(NAME|EMAIL|POSITION|DEPARTMENT|PHONENUMBER|)$";
    private final MemberService memberService;
    private final ProjectService projectService;

	@PostMapping
	public ApiResponse<MemberCreateWebResponse> memberCreateWebResponseApiResponse(
		@RequestBody final MemberCreateWebRequest memberCreateWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {

        final MemberCreateRequest memberCreateRequest = memberCreateWebRequest.toServiceDto();

		final UUID createdMemberId = memberService.createMember(memberCreateRequest, loginMemberDetail);

        final MemberCreateWebResponse memberCreateWebResponse = new MemberCreateWebResponse(createdMemberId);

        return ApiResponse.success(memberCreateWebResponse);
    }

	@DeleteMapping
	public ApiResponse<MemberDeleteWebResponse> deleteMember(
		@RequestBody MemberDeleteWebRequest memberDeleteWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {
		final UUID memberId = memberService.deleteMember(memberDeleteWebRequest.memberId(), loginMemberDetail);

        MemberDeleteWebResponse memberDeleteWebResponse = new MemberDeleteWebResponse(memberId);

        return ApiResponse.success(memberDeleteWebResponse);
    }

	@PutMapping
	public ApiResponse<MemberUpdateWebResponse> updateMember(
		@RequestBody MemberUpdateWebRequest memberUpdateWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {
		final MemberUpdateRequest memberUpdateRequest = memberUpdateWebRequest.toServiceDto();

		final UUID updatedId = memberService.updateMember(memberUpdateRequest, loginMemberDetail);

        final MemberUpdateWebResponse memberUpdateWebResponse = new MemberUpdateWebResponse(updatedId);

        return ApiResponse.success(memberUpdateWebResponse);
    }

    @GetMapping
    public ApiResponse<MemberListWebResponse> findMembersByOffset(
            @RequestParam(name = "page") @Min(value = 1, message = "{invalid.page-size}") final int page,
            @RequestParam(name = "keyword", required = false) final String keyword,
            @RequestParam(name = "keywordType", required = false) @Pattern(regexp = MEMBER_SEARCH_TYPE, message = "{member-search-type}") final String keywordType,
            @LoginMember LoginMemberDetail memberDetails) {

        final UUID companyId = MemberRole.SYSTEM_ADMIN.isSameRoleName(memberDetails.roleName()) ?
                null : memberDetails.companyId();

        final List<MemberSelectResponse> memberSelectResponses = memberService.findMembersBySearchWithPaging(
                page, keyword, keywordType, companyId);

        final long totalCount = memberService.countTotalmembersByCondition(keyword, keywordType, companyId);

        List<MemberSelectWebResponse> memberSelectWebResponses = memberSelectResponses.stream()
                .map(MemberSelectWebResponse::from)
                .toList();

        return ApiResponse.success(new MemberListWebResponse(memberSelectWebResponses, totalCount));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<MemberDetailsWebResponse> getMemberDetail(@PathVariable("memberId") final UUID memberId) {
        MemberDetailResponse memberDetailResponse = memberService.findMemberDetailByMemberId(memberId);

        List<MemberProjectInfoResponse> memberProjectInfoResponse = projectService.findProjectsAssignedMember(memberId);

        List<MemberProjectInfoWebResponse> memberAssignProjectWebResponses = memberProjectInfoResponse.stream()
                .map(MemberProjectInfoWebResponse::from)
                .toList();

        MemberDetailsWebResponse memberDetailsWebResponse = MemberDetailsWebResponse.from(memberDetailResponse,
                memberAssignProjectWebResponses);

        return ApiResponse.success(memberDetailsWebResponse);
    }

    @PostMapping("/resetPassword")
    public ApiResponse<ResetPasswordWebResponse> resetMemberPassword(
            @RequestBody ResetPasswordWebRequest resetPasswordWebRequest
    ) {
        final UUID memberId = memberService.resetMemberPassword(resetPasswordWebRequest);

        ResetPasswordWebResponse resetPasswordWebResponse = new ResetPasswordWebResponse(memberId);

        return ApiResponse.success(resetPasswordWebResponse);
    }

    @GetMapping("/{memberId}/myProjects")
    public ApiResponse<MemberProjectsListWebResponse> getMemberProjectInfo(@PathVariable("memberId") final UUID memberId) {

        List<MemberProjectInfoResponse> memberProjectInfoResponse = projectService.findProjectsAssignedMember(memberId);

        List<MemberProjectInfoWebResponse> memberAssignProjectWebResponses = memberProjectInfoResponse.stream()
                .map(MemberProjectInfoWebResponse::from)
                .toList();

        MemberProjectsListWebResponse memberProjectsListWebResponse = new MemberProjectsListWebResponse(memberAssignProjectWebResponses);
        return ApiResponse.success(memberProjectsListWebResponse);
    }
}
