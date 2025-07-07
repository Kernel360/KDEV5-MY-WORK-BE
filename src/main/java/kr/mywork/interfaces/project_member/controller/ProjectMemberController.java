package kr.mywork.interfaces.project_member.controller;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.project_member.service.ProjectMemberService;
import kr.mywork.domain.project_member.service.dto.request.ProjectManagerUpdateRequest;
import kr.mywork.domain.project_member.service.dto.response.CompanyMemberInProjectResponse;
import kr.mywork.interfaces.project_member.controller.dto.request.ProjectManagerUpdateWebRequest;
import kr.mywork.interfaces.project_member.controller.dto.response.CompanyMembersInProjectWebResponse;
import kr.mywork.interfaces.project_member.controller.dto.response.ProjectManagerUpdateWebResponse;
import kr.mywork.interfaces.project_member.controller.dto.response.ProjectMemberAddWebResponse;
import kr.mywork.interfaces.project_member.controller.dto.response.ProjectMemberDeleteWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-member")
@RequiredArgsConstructor
public class ProjectMemberController {

	final ProjectMemberService projectMemberService;

	@PostMapping("/member")// 프로젝트 멤버 추가
	public ApiResponse<ProjectMemberAddWebResponse> projectMemberAddWebResponseApiResponse(
		@RequestParam(name="projectId") UUID projectId,
		@RequestParam(name="memberId") UUID memberId,
		@LoginMember LoginMemberDetail loginMemberDetail
	) {

		final UUID addedMemberId = projectMemberService.addMemberToCompany(projectId,memberId, loginMemberDetail);

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
	@DeleteMapping  //전달 받은 멤버를 프로젝트 멤버에서 제외한다.
	public ApiResponse<ProjectMemberDeleteWebResponse> deleteProjectMember(
		@RequestParam(name="memberId") UUID memberId,
		@RequestParam(name="projectId") UUID projectId,
		@LoginMember LoginMemberDetail loginMemberDetail
	) {
		final UUID deletedMemberId =projectMemberService.deleteMemberById(memberId,projectId,loginMemberDetail);

		final ProjectMemberDeleteWebResponse projectMemberDeleteWebResponse = new ProjectMemberDeleteWebResponse(deletedMemberId);

		return ApiResponse.success(projectMemberDeleteWebResponse);
	}
	@PutMapping("/update-project-manager")
	public ApiResponse<ProjectManagerUpdateWebResponse> projectManagerUpdateWebResponseApiResponse(
			@RequestBody ProjectManagerUpdateWebRequest projectManagerUpdateWebRequest
	){
		final ProjectManagerUpdateRequest projectManagerUpdateRequest = projectManagerUpdateWebRequest.toServiceDto();

		final UUID updatedManagerId = projectMemberService.updateProjectManager(projectManagerUpdateRequest);

		final ProjectManagerUpdateWebResponse projectManagerUpdateWebResponse = new ProjectManagerUpdateWebResponse(updatedManagerId);

		return ApiResponse.success(projectManagerUpdateWebResponse);
	}
}

