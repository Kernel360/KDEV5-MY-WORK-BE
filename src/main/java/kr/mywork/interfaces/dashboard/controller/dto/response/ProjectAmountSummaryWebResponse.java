package kr.mywork.interfaces.dashboard.controller.dto.response;

import java.util.List;

public record ProjectAmountSummaryWebResponse(List<ProjectAmountChartWebResponse> chartData) {
}
