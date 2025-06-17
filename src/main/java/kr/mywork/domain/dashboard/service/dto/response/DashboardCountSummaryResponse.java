package kr.mywork.domain.dashboard.service.dto.response;

public record DashboardCountSummaryResponse(long totalCount, long inProgressCount, long completedCount) {
}
