package kr.mywork.interfaces.project_checklist.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.project_checklist.service.ProjectCheckListService;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListApprovalRequest;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListUpdateRequest;
import kr.mywork.domain.project_checklist.service.dto.response.CheckListProjectStepProgressResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListApprovalResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListCreateResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListDetailResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListSelectResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListUpdateResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.request.ProjectCheckListApprovalWebRequest;
import kr.mywork.interfaces.project_checklist.controller.dto.request.ProjectCheckListCreateWebRequest;
import kr.mywork.interfaces.project_checklist.controller.dto.request.ProjectCheckListUpdateWebRequest;
import kr.mywork.interfaces.project_checklist.controller.dto.response.CheckListsSelectWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListApprovalWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListCreateWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListDeleteWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListDetailWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListProgressListWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListProgressWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListSelectWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListUpdateWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProjectCheckListController {

	private final ProjectCheckListService projectCheckListService;

	@PostMapping("/api/projects/check-lists")
	public ApiResponse<ProjectCheckListCreateWebResponse> createProjectCheckList(
		@RequestBody @Valid ProjectCheckListCreateWebRequest projectCheckListCreateWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail
	) {

		ProjectCheckListCreateRequest projectCheckListCreateRequest = projectCheckListCreateWebRequest.toServiceDto();

		ProjectCheckListCreateResponse projectCheckListCreateResponse = projectCheckListService.createProjectCheckList(
			projectCheckListCreateRequest, loginMemberDetail);

		return ApiResponse.success(new ProjectCheckListCreateWebResponse(projectCheckListCreateResponse));
	}

	@GetMapping("/api/projects/check-lists/{checkListId}")
	public ApiResponse<ProjectCheckListDetailWebResponse> getProjectCheckList(
		@PathVariable final UUID checkListId) {

		ProjectCheckListDetailResponse projectCheckListDetailResponse = projectCheckListService.getProjectCheckList(
			checkListId);

		return ApiResponse.success(new ProjectCheckListDetailWebResponse(projectCheckListDetailResponse));
	}

	@PutMapping("/api/projects/check-lists")
	public ApiResponse<ProjectCheckListUpdateWebResponse> getProjectCheckList(
		@RequestBody @Valid ProjectCheckListUpdateWebRequest projectCheckListUpdateWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {
		ProjectCheckListUpdateRequest projectCheckListUpdateRequest = projectCheckListUpdateWebRequest.toServiceDto();

		ProjectCheckListUpdateResponse projectCheckListUpdateResponse = projectCheckListService.updateProjectCheckList(
			projectCheckListUpdateRequest, loginMemberDetail);

		return ApiResponse.success(new ProjectCheckListUpdateWebResponse(projectCheckListUpdateResponse));
	}

	@DeleteMapping("/api/projects/check-lists/{checkListId}")
	public ApiResponse<ProjectCheckListDeleteWebResponse> deleteProjectCheckList(
		@PathVariable final UUID checkListId,
		@LoginMember LoginMemberDetail loginMemberDetail) {

		UUID deletedCheckListId = projectCheckListService.deleteProjectCheckList(
			checkListId, loginMemberDetail);

		return ApiResponse.success(new ProjectCheckListDeleteWebResponse(deletedCheckListId));
	}

	@PutMapping("/api/projects/check-lists/{checklistId}/approval")
	public ApiResponse<ProjectCheckListApprovalWebResponse> approvalProjectCheckList(
		@RequestBody @Valid ProjectCheckListApprovalWebRequest projectCheckListApprovalWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {
		ProjectCheckListApprovalRequest projectCheckListApprovalRequest = projectCheckListApprovalWebRequest.toServiceDto();

		ProjectCheckListApprovalResponse projectCheckListApprovalResponse = projectCheckListService.approvalProjectCheckList(
			projectCheckListApprovalRequest, loginMemberDetail);

		return ApiResponse.success(new ProjectCheckListApprovalWebResponse(projectCheckListApprovalResponse));
	}

	@GetMapping("/api/projects/{projectId}/check-list/progress")
	public ApiResponse<ProjectCheckListProgressListWebResponse> getProjectCheckListProgress(
		@PathVariable(name = "projectId") final UUID projectId) {

		final List<CheckListProjectStepProgressResponse> checkListProjectStepProgressResponses =
			projectCheckListService.getCheckListProgress(projectId, "APPROVED");

		final List<ProjectCheckListProgressWebResponse> projectCheckListProgressWebResponses =
			checkListProjectStepProgressResponses.stream()
				.map(ProjectCheckListProgressWebResponse::fromServiceResponse)
				.toList();

		return ApiResponse.success(new ProjectCheckListProgressListWebResponse(projectCheckListProgressWebResponses));
	}

	@GetMapping("/api/projects/{projectId}/check-list")
	public ApiResponse<CheckListsSelectWebResponse> findCheckListByProjectIdAndProjectStatusId(
		@PathVariable("projectId") final UUID projectId, @RequestParam(required = false) final UUID projectStepId) {

		final List<ProjectCheckListSelectResponse> projectCheckListSelectResponses =
			projectCheckListService.findAllByProjectIdAndProjectStepId(projectId, projectStepId);

		List<ProjectCheckListSelectWebResponse> projectCheckListSelectWebResponses =
			projectCheckListSelectResponses.stream()
				.map(ProjectCheckListSelectWebResponse::fromServiceResponse)
				.toList();

		return ApiResponse.success(new CheckListsSelectWebResponse(projectCheckListSelectWebResponses));
	}
}
