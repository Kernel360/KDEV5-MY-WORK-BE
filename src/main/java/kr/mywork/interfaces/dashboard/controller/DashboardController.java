package kr.mywork.interfaces.dashboard.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.dashboard.service.dto.response.DashboardCountSummaryResponse;
import kr.mywork.domain.dashboard.service.dto.response.DashboardPopularProjectsResponse;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.domain.project.service.dto.request.NearDeadlineProjectRequest;
import kr.mywork.domain.project.service.dto.response.NearDeadlineProjectResponse;
import kr.mywork.domain.project.service.dto.response.ProjectAmountSummaryResponse;
import kr.mywork.interfaces.dashboard.controller.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Validated
public class DashboardController {

	private static final String AMOUNT_CHART_TYPE = "^(CHART_TYPE_WEEK|CHART_TYPE_MONTH)$";

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

	@GetMapping("/projects/near-deadline")
	public ApiResponse<NearDeadlineProjectListWebResponse> getNearDeadlineProjects(
		@RequestParam(name = "page") @Min(value = 1, message = "{invalid.page-size}") final int page,
		@LoginMember final LoginMemberDetail loginMemberDetail
	) {

		LocalDate today = LocalDate.now();

		NearDeadlineProjectRequest nearDeadlineProjectWebRequest = new NearDeadlineProjectRequest(
			loginMemberDetail.memberId(),
			loginMemberDetail.companyId(),
			loginMemberDetail.roleName()
		);

		final List<NearDeadlineProjectResponse> NearDeadlineProjectResponse =
			projectService.findNearDeadlineProjectsByLoginMember(page, nearDeadlineProjectWebRequest, today);

		final long totalCount =
			projectService.countNearDeadlineProjectsByLoginMember(nearDeadlineProjectWebRequest, today);

		final List<NearDeadlineProjectWebResponse> nearDeadlineProjectWebResponses = NearDeadlineProjectResponse.stream()
			.map(NearDeadlineProjectWebResponse::fromServiceResponse)
			.toList();

		final NearDeadlineProjectListWebResponse response =
			new NearDeadlineProjectListWebResponse(nearDeadlineProjectWebResponses, totalCount);

		return ApiResponse.success(response);
	}
	@GetMapping("/project-amount")
	public ApiResponse<ProjectAmountSummaryWebResponse> getProjectAmountSummary(
			@LoginMember final LoginMemberDetail loginMemberDetail,
			@RequestParam(name = "chartType")
			@Pattern(regexp = AMOUNT_CHART_TYPE, message = "{dashboard.invalid.amount-char-type}") String chartType){
		LocalDate today = LocalDate.now();
		//날짜, totalCount
		final List<ProjectAmountSummaryResponse> projectAmountSummaryList = projectService.findProjectAmountSummary(loginMemberDetail,chartType,today);

		final List<ProjectAmountChartWebResponse> projectAmountChartWebResponses = projectAmountSummaryList.stream()
				.map(ProjectAmountChartWebResponse::from)
				.toList();

		final ProjectAmountSummaryWebResponse projectAmountSummaryWebResponse = new ProjectAmountSummaryWebResponse(projectAmountChartWebResponses);

		return ApiResponse.success(projectAmountSummaryWebResponse);
	}
}
