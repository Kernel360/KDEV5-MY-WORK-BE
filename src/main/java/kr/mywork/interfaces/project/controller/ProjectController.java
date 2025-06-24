package kr.mywork.interfaces.project.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.domain.project.service.dto.request.ProjectCreateRequest;
import kr.mywork.domain.project.service.dto.request.ProjectUpdateRequest;
import kr.mywork.domain.project.service.dto.response.*;
import kr.mywork.interfaces.project.controller.dto.request.ProjectCreateWebRequest;
import kr.mywork.interfaces.project.controller.dto.request.ProjectDeleteWebRequest;
import kr.mywork.interfaces.project.controller.dto.request.ProjectUpdateWebRequest;
import kr.mywork.interfaces.project.controller.dto.response.*;
import kr.mywork.interfaces.project.dto.response.ProjectMemberListWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@Validated
@RequiredArgsConstructor
public class ProjectController {

	private static final String PROJECT_SEARCH_KEYWORD_TYPE = "^(PROJECT_NAME|DEV_COMPANY_NAME|CLIENT_COMPANY_NAME)$";
	//private static final String PROJECT_STEP_TYPE = "^(NOT_STARTED|IN_PROGRESS|PAUSED|COMPLETED)$";
	private static final String PROJECT_STEP_TYPE = "^(CONTRACT|IN_PROGRESS|PAYMENT|COMPLETED)$";

	private final ProjectService projectService;

	@PostMapping
	public ApiResponse<ProjectCreateWebResponse> createProject(
		@RequestBody @Valid final ProjectCreateWebRequest projectCreateWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {

		final ProjectCreateRequest projectCreateRequest = projectCreateWebRequest.toServiceDto();

		final UUID createdId = projectService.createProject(projectCreateRequest, loginMemberDetail);

		return ApiResponse.success(new ProjectCreateWebResponse(createdId));
	}

	@PutMapping("/{projectId}")
	public ApiResponse<ProjectUpdateWebResponse> updateProject(
		@RequestBody @Valid final ProjectUpdateWebRequest webRequest,
		@PathVariable final UUID projectId,
		@LoginMember LoginMemberDetail loginMemberDetail) {

		final ProjectUpdateRequest projectUpdateRequest = webRequest.toServiceDto();

		final ProjectUpdateResponse serviceResponse = projectService.updateProject(projectId, projectUpdateRequest, loginMemberDetail);

		final ProjectUpdateWebResponse webResponse = ProjectUpdateWebResponse.from(serviceResponse);

		return ApiResponse.success(webResponse);
	}

	@DeleteMapping
	public ApiResponse<ProjectDeleteWebResponse> deleteProject(
		@RequestBody final ProjectDeleteWebRequest webRequest,
		@LoginMember LoginMemberDetail loginMemberDetail
	) {
		// 1) WebRequest → service 로직 (ID만 필요)
		final UUID deletedId = projectService.deleteProject(webRequest.getId(), loginMemberDetail);

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
		@RequestParam(name = "keywordType", required = false)
		@Pattern(regexp = PROJECT_SEARCH_KEYWORD_TYPE, message = "{project.invalid-search-keyword-type}") final String keywordType,
		@RequestParam(name = "keyword", required = false) final String keyword,
		@RequestParam(name = "step", required = false)
		@Pattern(regexp = PROJECT_STEP_TYPE, message = "{project.invalid-status}") final String step,
		@RequestParam(name = "page") @Min(value = 1, message = "{invalid.page}") final int page) {

		List<ProjectSelectResponse> projectSelectResponses =
			projectService.findProjectsBySearchConditionWithPaging(keywordType, keyword, step, page);

		List<ProjectSelectWebResponse> webList = projectSelectResponses.stream()
			.map(ProjectSelectWebResponse::from)
			.collect(Collectors.toList());

		long totalCount = projectService.countTotalProjectsByCondition(keywordType, keyword, step);

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

	@GetMapping("my-projects")
	public ApiResponse<MyProjectListWebResponse> myProjectList(@LoginMember LoginMemberDetail loginMemberDetail){

		final List<MyProjectSelectResponse> projectList =  projectService.findProjectsByLoginMember(loginMemberDetail);

		final List<MyProjectSelectWebResponse> webList = projectList.stream()
				.map(MyProjectSelectWebResponse::from)
				.toList();

		return ApiResponse.success(new MyProjectListWebResponse(webList));
	}
}
