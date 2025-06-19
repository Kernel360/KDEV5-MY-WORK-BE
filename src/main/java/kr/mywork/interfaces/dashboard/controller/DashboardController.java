package kr.mywork.interfaces.dashboard.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.dashboard.service.DashboardService;
import kr.mywork.domain.dashboard.service.dto.response.DashboardPopularProjectsResponse;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardCountSummaryWebResponse;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardPopularProjectListWebResponse;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardPopularProjectWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Validated
public class DashboardController {

	private final DashboardService dashboardService;
	private final ProjectService projectService;

	@GetMapping("/totalSummery")
	public ApiResponse<DashboardCountSummaryWebResponse> getDashboardTotalCount(
			@LoginMember LoginMemberDetail loginMemberDetail) {

		final DashboardCountSummaryWebResponse dashboardCountSummaryWebResponse = dashboardService.getSummaryTotalCount(
				loginMemberDetail);

		return ApiResponse.success(dashboardCountSummaryWebResponse);

	}
	@GetMapping("/popularProjects")
	public    ApiResponse<DashboardPopularProjectListWebResponse> getPopularProjects(
		@LoginMember final LoginMemberDetail memberDetail
	){
		final List<DashboardPopularProjectsResponse> popularProjects = projectService.getPopularProjects(memberDetail);

		final List<DashboardPopularProjectWebResponse> webResponse = popularProjects.stream()
			.map(DashboardPopularProjectWebResponse::from)
			.toList();

		final DashboardPopularProjectListWebResponse response = new DashboardPopularProjectListWebResponse(webResponse);

		return ApiResponse.success(response);

	}

}
