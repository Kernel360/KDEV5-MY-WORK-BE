package kr.mywork.interfaces.member.controller;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.member.service.MemberService;
import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;
import kr.mywork.interfaces.member.controller.dto.request.MemberCreateWebRequest;
import kr.mywork.interfaces.member.controller.dto.response.MemberCreateWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ApiResponse<MemberCreateWebResponse> createMember(
            @RequestBody final MemberCreateWebRequest memberCreateWebRequest) {

        final MemberCreateRequest memberCreateRequest = memberCreateWebRequest.toServiceDto();

        final Long createdMemberId = memberService.createMember(memberCreateRequest);

        final MemberCreateWebResponse memberCreateWebResponse = new MemberCreateWebResponse(createdMemberId);

        return ApiResponse.success(memberCreateWebResponse);
    }
}
