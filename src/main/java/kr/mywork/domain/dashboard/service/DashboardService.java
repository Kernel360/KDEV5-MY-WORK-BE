package kr.mywork.domain.dashboard.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.mywork.domain.dashboard.service.dto.response.DashboardCountSummaryResponse;
import kr.mywork.domain.dashboard.service.errors.DashboardErrorType;
import kr.mywork.domain.dashboard.service.errors.DashboardTypeNotFoundException;
import kr.mywork.domain.dashboard.service.repository.DashboardRepository;
import kr.mywork.domain.project.model.ProjectAssign;
import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.interfaces.dashboard.controller.dto.request.DashboardCountSummaryWebRequest;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardCountSummaryWebResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

	final DashboardRepository dashboardRepository;

	public DashboardCountSummaryWebResponse getSummaryTotalCount(DashboardCountSummaryWebRequest webRequest) {

		final String userType = webRequest.userType();
		final UUID companyId = webRequest.companyId();
		final UUID memberId = webRequest.memberId();

		if ("SYSTEMADMIN".equalsIgnoreCase(userType)) {
			return getAdminSummary();
		} else if (("DEVADMIN".equalsIgnoreCase(userType) || "CLIENTADMIN".equalsIgnoreCase(userType))
			&& companyId != null) {
			return getCompanyAdminSummary(companyId, userType);
		} else if ("USER".equalsIgnoreCase(userType) && memberId != null) {
			return getUserSummary(memberId);
		} else {
			throw new DashboardTypeNotFoundException(DashboardErrorType.TYPE_NOT_FOUND);
		}
	}

	private DashboardCountSummaryWebResponse getAdminSummary() {

		final DashboardCountSummaryResponse dashboardCountSummaryResponse = dashboardRepository.getAdminSummary();

		return DashboardCountSummaryWebResponse.from(dashboardCountSummaryResponse);

	}

	private DashboardCountSummaryWebResponse getCompanyAdminSummary(UUID companyId, String userType) {

		final String type = extractCompanyAdminType(userType);

		final List<UUID> projectIds = dashboardRepository.getCompanyAdminProject(companyId, type).stream()
			.map(ProjectAssign::getProjectId)
			.toList();

		final DashboardCountSummaryResponse dashboardCountSummaryResponse = dashboardRepository.getCompanyAdminSummary(
			projectIds);

		return DashboardCountSummaryWebResponse.from(dashboardCountSummaryResponse);
	}

	private DashboardCountSummaryWebResponse getUserSummary(UUID memberId) {

		final List<UUID> projectIds = dashboardRepository.getUserProject(memberId).stream()
			.map(ProjectMember::getProjectId)
			.toList();

		final DashboardCountSummaryResponse dashboardCountSummaryResponse = dashboardRepository.getUserSummary(projectIds);

		return DashboardCountSummaryWebResponse.from(dashboardCountSummaryResponse);
	}

	private String extractCompanyAdminType(String userType) {
		if (userType.equalsIgnoreCase("SYSTEMADMIN"))
			return "system";
		if (userType.equalsIgnoreCase("DEVADMIN"))
			return "dev";
		if (userType.equalsIgnoreCase("CLIENTADMIN"))
			return "client";
		return "user";
	}
}
