package kr.mywork.interfaces.dashboard.controller.dto.request;

import java.util.UUID;

public record DashboardCountSummaryWebRequest(String userType , UUID companyId, UUID memberId) {
}
