package kr.mywork.interfaces.project_step.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project_step.serivce.ProjectStepService;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepCreateRequest;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepsCreateWebRequest;
import kr.mywork.interfaces.project_step.dto.response.ProjectStepsCreateWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class ProjectStepController {

	private final ProjectStepService projectStepService;

	@PostMapping("/api/projects/steps")
	public ApiResponse<ProjectStepsCreateWebResponse> createProjectSteps(
		@RequestBody @Valid ProjectStepsCreateWebRequest projectStepsCreateWebRequest) {

		final UUID projectId = projectStepsCreateWebRequest.getProjectId();
		final List<ProjectStepCreateRequest> serviceRequests = projectStepsCreateWebRequest.toServiceRequests();

		final Integer createdCount = projectStepService.saveAll(projectId, serviceRequests);

		return ApiResponse.success(new ProjectStepsCreateWebResponse(createdCount));
	}
}
