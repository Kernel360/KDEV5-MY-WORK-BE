package kr.mywork.interfaces.project.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.domain.project.service.dto.response.ProjectMemberResponse;
import kr.mywork.interfaces.project.dto.response.ProjectMemberListWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/projects")
public class ProjectController {

	private final ProjectService projectService;

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
