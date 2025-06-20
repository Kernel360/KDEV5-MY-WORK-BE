package kr.mywork.interfaces.dashboard.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Min;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.dashboard.service.DashboardService;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.domain.project.service.dto.response.NearDeadlineProjectResponse;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardCountSummaryWebResponse;
import kr.mywork.interfaces.dashboard.controller.dto.response.NearDeadlineProjectWebResponse;
import kr.mywork.interfaces.dashboard.controller.dto.response.NearDeadlineProjectListWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/projects/near-deadline")
	public ApiResponse<NearDeadlineProjectListWebResponse> getNearDeadlineProjects(
		@RequestParam(name = "page") @Min(value = 1, message = "{invalid.page-size}") final int page,
		@LoginMember final LoginMemberDetail loginMemberDetail
	) {

		LocalDate today = LocalDate.now();

		final List<NearDeadlineProjectResponse> NearDeadlineProjectResponse =
			projectService.findNearDeadlineProjectsByLoginMember(page, loginMemberDetail, today);

		final long totalCount =
			projectService.countNearDeadlineProjectsByLoginMember(loginMemberDetail, today);

		final List<NearDeadlineProjectWebResponse> nearDeadlineProjectWebResponses = NearDeadlineProjectResponse.stream()
			.map(NearDeadlineProjectWebResponse::fromServiceResponse)
			.toList();

		final NearDeadlineProjectListWebResponse response =
			new NearDeadlineProjectListWebResponse(nearDeadlineProjectWebResponses, totalCount);

		return ApiResponse.success(response);
	}
}
