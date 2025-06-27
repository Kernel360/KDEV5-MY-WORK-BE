package kr.mywork.interfaces.dashboard.controller.dto.response;

import kr.mywork.domain.project.service.dto.response.ProjectAmountSummaryResponse;

public record ProjectAmountChartWebResponse (String label, long totalAmount){
    public static ProjectAmountChartWebResponse from(ProjectAmountSummaryResponse response){
        return new ProjectAmountChartWebResponse(response.label(),response.totalAmount());
    }
}
