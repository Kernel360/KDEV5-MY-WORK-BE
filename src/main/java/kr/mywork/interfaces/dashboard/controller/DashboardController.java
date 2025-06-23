package kr.mywork.interfaces.dashboard.controller;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.dashboard.service.dto.response.DashboardCountSummaryResponse;
import kr.mywork.domain.dashboard.service.dto.response.DashboardPopularProjectsResponse;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardCountSummaryWebResponse;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardPopularProjectListWebResponse;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardPopularProjectWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Validated
public class DashboardController {

	private final ProjectService projectService;

	@GetMapping("/total-summary")
	public ApiResponse<DashboardCountSummaryWebResponse> getDashboardTotalCount(
			@LoginMember LoginMemberDetail loginMemberDetail) {

		final DashboardCountSummaryResponse dashboardCountSummaryResponse = projectService.getSummaryTotalCount(loginMemberDetail);

		final DashboardCountSummaryWebResponse dashboardCountSummaryWebResponse = DashboardCountSummaryWebResponse.from(dashboardCountSummaryResponse);

		return ApiResponse.success(dashboardCountSummaryWebResponse);

	}
	@GetMapping("/popular-projects")
	public    ApiResponse<DashboardPopularProjectListWebResponse> getMostPostProjectsTopFive(
		@LoginMember final LoginMemberDetail memberDetail
	){
		final List<DashboardPopularProjectsResponse> popularProjects = projectService.getMostPostProjectsTopFive(memberDetail);

		final List<DashboardPopularProjectWebResponse> webResponse = popularProjects.stream()
			.map(DashboardPopularProjectWebResponse::from)
			.toList();

		final DashboardPopularProjectListWebResponse response = new DashboardPopularProjectListWebResponse(webResponse);

		return ApiResponse.success(response);

	}

}
