package kr.mywork.interfaces.project_checklist.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project_checklist.service.ProjectCheckListService;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListCreateResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.ProjectCheckListCreateWebResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.request.ProjectCheckListCreateWebRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProjectCheckListController {

	private final ProjectCheckListService projectCheckListService;

	@PostMapping("/api/projects/check-lists")
	public ApiResponse<ProjectCheckListCreateWebResponse> createProjectCheckList(
		@RequestBody @Valid ProjectCheckListCreateWebRequest projectCheckListCreateWebRequest
	) {

		ProjectCheckListCreateRequest projectCheckListCreateRequest = projectCheckListCreateWebRequest.toServiceDto();

		ProjectCheckListCreateResponse projectCheckListCreateResponse = projectCheckListService.createProjectCheckList(
			projectCheckListCreateRequest);

		return ApiResponse.success(new ProjectCheckListCreateWebResponse(projectCheckListCreateResponse));
	}

}
