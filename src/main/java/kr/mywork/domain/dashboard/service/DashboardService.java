package kr.mywork.domain.dashboard.service;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.dashboard.service.dto.response.DashboardCountSummaryResponse;
import kr.mywork.domain.dashboard.service.errors.DashboardErrorType;
import kr.mywork.domain.dashboard.service.errors.DashboardTypeNotFoundException;
import kr.mywork.domain.dashboard.service.repository.DashboardRepository;
import kr.mywork.domain.project.model.ProjectAssign;
import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.interfaces.dashboard.controller.dto.response.DashboardCountSummaryWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DashboardService {

	final DashboardRepository dashboardRepository;

	public DashboardCountSummaryWebResponse getSummaryTotalCount(LoginMemberDetail loginMemberDetail) {

		final String userType = loginMemberDetail.roleName();
		final UUID companyId = loginMemberDetail.companyId();
		final UUID memberId = loginMemberDetail.memberId();

		if ("ROLE_SYSTEM_ADMIN".equalsIgnoreCase(userType)) {
			return getAdminSummary();
		} else if (("ROLE_DEV_ADMIN".equalsIgnoreCase(userType) || "ROLE_CLIENT_ADMIN".equalsIgnoreCase(userType))
			&& companyId != null) {
			return getCompanyAdminSummary(companyId, userType);
		} else if ("ROLE_USER".equalsIgnoreCase(userType) && memberId != null) {
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
		if (userType.equalsIgnoreCase("ROLE_SYSTEM_ADMIN"))
			return "system";
		if (userType.equalsIgnoreCase("ROLE_DEV_ADMIN"))
			return "dev";
		if (userType.equalsIgnoreCase("ROLE_CLIENT_ADMIN"))
			return "client";
		return "user";
	}
}
