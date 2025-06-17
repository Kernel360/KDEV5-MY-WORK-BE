package kr.mywork.interfaces.dashboard.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.dashboard.service.DashboardService;
import kr.mywork.interfaces.dashboard.controller.dto.request.DashboardCountSummaryWebRequest;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardCountSummaryWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Validated
public class DashboardController {

	private final DashboardService dashboardService;

	@GetMapping("/totalSummery")
	public ApiResponse<DashboardCountSummaryWebResponse> getDashboardTotalCount(
		@RequestBody DashboardCountSummaryWebRequest dashboardCountSummaryWebRequest) {

		final DashboardCountSummaryWebResponse dashboardCountSummaryWebResponse = dashboardService.getSummaryTotalCount(
			dashboardCountSummaryWebRequest);

		return ApiResponse.success(dashboardCountSummaryWebResponse);

	}

}
