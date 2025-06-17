package kr.mywork.interfaces.dashboard.controller.dto.response;

import kr.mywork.domain.dashboard.service.dto.response.DashboardCountSummaryResponse;

public record DashboardCountSummaryWebResponse(long totalCount, long inProgressCount, long completedCount) {


	public static DashboardCountSummaryWebResponse from(DashboardCountSummaryResponse response) {
		return new DashboardCountSummaryWebResponse(
			response.totalCount(), response.inProgressCount(), response.completedCount()
		);
	}

}
