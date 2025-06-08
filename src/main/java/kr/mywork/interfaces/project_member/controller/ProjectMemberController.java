package kr.mywork.interfaces.project_member.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project_member.service.ProjectMemberService;
import kr.mywork.domain.project_member.service.dto.response.CompanyMemberInProjectResponse;
import kr.mywork.interfaces.project_member.controller.dto.response.CompanyMembersInProjectWebResponse;
import kr.mywork.interfaces.project_member.controller.dto.response.ProjectMemberAddWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/project-member")
@RequiredArgsConstructor
public class ProjectMemberController {

	final ProjectMemberService projectMemberService;

	@PostMapping("/member")// 프로젝트 멤버 추가
	public ApiResponse<ProjectMemberAddWebResponse> projectMemberAddWebResponseApiResponse(
		@RequestParam(name="projectId") UUID projectId,
		@RequestParam(name="memberId") UUID memberId
	) {

		final UUID addedMemberId = projectMemberService.addMemberToCompany(projectId,memberId);

		final ProjectMemberAddWebResponse projectMemberAddWebResponse = new ProjectMemberAddWebResponse(addedMemberId);

		return ApiResponse.success(projectMemberAddWebResponse);
	}
	@GetMapping	//프로젝트에 참여중인 내 회사의 모든 멤버를 조회.
	public ApiResponse<CompanyMembersInProjectWebResponse> findCompanyMembersInProject(
		@RequestParam(name="projectId") UUID projectId,
		@RequestParam(name="companyId") UUID companyId
		){
		final List<CompanyMemberInProjectResponse> companyMemberInProjectResponseList = projectMemberService.findCompanyMembersInProject(projectId,companyId);

		return ApiResponse.success(CompanyMembersInProjectWebResponse.from(companyMemberInProjectResponseList));
		
	}
}

