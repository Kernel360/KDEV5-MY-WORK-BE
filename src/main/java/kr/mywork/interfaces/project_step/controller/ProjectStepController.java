package kr.mywork.interfaces.project_step.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.service.PostService;
import kr.mywork.domain.project_step.serivce.ProjectStepService;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepCreateRequest;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepUpdateRequest;
import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepGetResponse;
import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepPostTotalCountResponse;
import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepUpdateResponse;
import kr.mywork.interfaces.post.controller.dto.response.ProjectStepsGetWebResponse;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepsCreateWebRequest;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepsUpdateWebRequest;
import kr.mywork.interfaces.project_step.dto.response.ProjectStepUpdateWebResponse;
import kr.mywork.interfaces.project_step.dto.response.ProjectStepsCreateWebResponse;
import kr.mywork.interfaces.project_step.dto.response.ProjectStepsUpdateWebResponse;
import kr.mywork.interfaces.project_step.dto.response.ProjectStepsWithPostTotalCountWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class ProjectStepController {

	private final ProjectStepService projectStepService;
	private	final PostService postService;

	@PostMapping("/api/projects/steps")
	public ApiResponse<ProjectStepsCreateWebResponse> createProjectSteps(
		@RequestBody @Valid ProjectStepsCreateWebRequest projectStepsCreateWebRequest) {

		final UUID projectId = projectStepsCreateWebRequest.getProjectId();
		final List<ProjectStepCreateRequest> serviceRequests = projectStepsCreateWebRequest.toServiceRequests();

		final Integer createdCount = projectStepService.saveAll(projectId, serviceRequests);

		return ApiResponse.success(new ProjectStepsCreateWebResponse(createdCount));
	}

	@PutMapping("/api/projects/{projectId}/steps")
	public ApiResponse<ProjectStepsUpdateWebResponse> updateProjectSteps(
		@PathVariable(name = "projectId") UUID projectId,
		@RequestBody @Valid ProjectStepsUpdateWebRequest projectStepsUpdateWebRequest) {
		final List<ProjectStepUpdateRequest> projectStepUpdateRequests = projectStepsUpdateWebRequest.toServiceRequests();

		final List<ProjectStepUpdateResponse> projectStepUpdateResponses =
			projectStepService.updateProjectSteps(projectId, projectStepUpdateRequests);

		final List<ProjectStepUpdateWebResponse> projectStepUpdateWebResponses =
			ProjectStepsUpdateWebResponse.fromServiceResponses(projectStepUpdateResponses);

		return ApiResponse.success(new ProjectStepsUpdateWebResponse(projectStepUpdateWebResponses));
	}
	@GetMapping("/api/projects/{projectId}/steps")
	public ApiResponse<ProjectStepsGetWebResponse> getProjectSteps(
		@PathVariable(name = "projectId") UUID projectId
	){
		final List<ProjectStepGetResponse> projectStepGetResponses = projectStepService.getProjectSteps(projectId);

		final ProjectStepsGetWebResponse projectStepGetWebResponse = new ProjectStepsGetWebResponse(projectStepGetResponses);

		return ApiResponse.success(projectStepGetWebResponse);
	}
	@GetMapping("/api/projects/{projectId}/steps-with-count")
	public ApiResponse<ProjectStepsWithPostTotalCountWebResponse> getProjectStepsWithCount(
		@PathVariable(name="projectId") UUID projectId
	){
		final List<ProjectStepGetResponse> projectStepGetResponses = projectStepService.getProjectSteps(projectId);

		final List<ProjectStepPostTotalCountResponse> projectStepsWithPostTotalCountResponses = postService.getProjectStepsWithPostTotalCount(projectStepGetResponses);

		final ProjectStepsWithPostTotalCountWebResponse projectStepsWithCountWebResponse = new ProjectStepsWithPostTotalCountWebResponse(projectStepsWithPostTotalCountResponses);

		return ApiResponse.success(projectStepsWithCountWebResponse);
	}
}
