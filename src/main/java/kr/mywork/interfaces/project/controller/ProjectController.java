package kr.mywork.interfaces.project.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.domain.project.service.dto.request.ProjectCreateRequest;
import kr.mywork.domain.project.service.dto.request.ProjectUpdateRequest;
import kr.mywork.domain.project.service.dto.response.ProjectDetailResponse;
import kr.mywork.domain.project.service.dto.response.ProjectMemberResponse;
import kr.mywork.domain.project.service.dto.response.ProjectSelectWithAssignResponse;
import kr.mywork.domain.project.service.dto.response.ProjectUpdateResponse;
import kr.mywork.interfaces.project.controller.dto.request.ProjectCreateWebRequest;
import kr.mywork.interfaces.project.controller.dto.request.ProjectDeleteWebRequest;
import kr.mywork.interfaces.project.controller.dto.request.ProjectUpdateWebRequest;
import kr.mywork.interfaces.project.controller.dto.response.ProjectCreateWebResponse;
import kr.mywork.interfaces.project.controller.dto.response.ProjectDeleteWebResponse;
import kr.mywork.interfaces.project.controller.dto.response.ProjectDetailWebResponse;
import kr.mywork.interfaces.project.controller.dto.response.ProjectListWebResponse;
import kr.mywork.interfaces.project.controller.dto.response.ProjectSelectWebResponse;
import kr.mywork.interfaces.project.controller.dto.response.ProjectUpdateWebResponse;
import kr.mywork.interfaces.project.dto.response.ProjectMemberListWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@Validated
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectService projectService;

	@PostMapping
	public ApiResponse<ProjectCreateWebResponse> createProject(
		@RequestBody @Valid final ProjectCreateWebRequest projectCreateWebRequest) {

		final ProjectCreateRequest projectCreateRequest = projectCreateWebRequest.toServiceDto();

		final UUID createdId = projectService.createProject(projectCreateRequest);

		return ApiResponse.success(new ProjectCreateWebResponse(createdId));
	}

	@PutMapping("/{projectId}")
	public ApiResponse<ProjectUpdateWebResponse> updateProject(
		@RequestBody @Valid final ProjectUpdateWebRequest webRequest,
		@PathVariable final UUID projectId
	) {
		final ProjectUpdateRequest dto = webRequest.toServiceDto(projectId);

		final ProjectUpdateResponse serviceResponse = projectService.updateProject(dto);

		final ProjectUpdateWebResponse webResponse = ProjectUpdateWebResponse.from(serviceResponse);

		return ApiResponse.success(webResponse);
	}

	@DeleteMapping
	public ApiResponse<ProjectDeleteWebResponse> deleteProject(
		@RequestBody final ProjectDeleteWebRequest webRequest
	) {
		// 1) WebRequest → service 로직 (ID만 필요)
		final UUID deletedId = projectService.deleteProject(webRequest.getId());

		// 2) WebResponse 생성
		return ApiResponse.success(new ProjectDeleteWebResponse(deletedId));
	}

	@GetMapping("/{projectId}")
	public ApiResponse<ProjectDetailWebResponse> getProjectDetail(
		@PathVariable("projectId") final UUID projectId
	) {
		// 1) 서비스에서 단건 조회
		ProjectDetailResponse projectDetailResponse = projectService.findProjectDetailsById(projectId);

		// 2) ServiceResponse → WebResponse
		ProjectDetailWebResponse projectDetailWebResponse = ProjectDetailWebResponse.from(projectDetailResponse);

		return ApiResponse.success(projectDetailWebResponse);
	}

	@GetMapping
	public ApiResponse<ProjectListWebResponse> listProjects(
		@RequestParam(name = "page") @Min(value = 1, message = "{invalid.page}") final int page,
		@RequestParam(name = "memberId", required = false) final UUID memberId,
		@RequestParam(name = "nameKeyword", required = false) final String nameKeyword,
		@RequestParam(name = "deleted", required = false) final Boolean deleted
	) {
		List<ProjectSelectWithAssignResponse> serviceList =
			projectService.findProjectsBySearchConditionWithPaging(page, memberId, nameKeyword, deleted);

		List<ProjectSelectWebResponse> webList = serviceList.stream()
			.map(ProjectSelectWebResponse::from)
			.collect(Collectors.toList());

		long totalCount = projectService.countTotalProjectsByCondition(memberId, nameKeyword, deleted);

		return ApiResponse.success(new ProjectListWebResponse(webList, totalCount));
	}

	@GetMapping("/members")
	public ApiResponse<ProjectMemberListWebResponse> projectMemberListWebResponseApiResponse(
		@RequestParam(name = "companyId") UUID companyId,
		@RequestParam(name = "projectId") UUID projectId
	) {
		List<ProjectMemberResponse> projectMemberResponses = projectService.findMemberByCompanyId(companyId, projectId);

		ProjectMemberListWebResponse projectMemberListWebResponse = ProjectMemberListWebResponse.from(
			projectMemberResponses);

		return ApiResponse.success(projectMemberListWebResponse);
	}
}
