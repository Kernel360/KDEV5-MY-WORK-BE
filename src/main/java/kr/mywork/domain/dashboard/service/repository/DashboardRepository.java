package kr.mywork.domain.dashboard.service.repository;

import java.util.List;
import java.util.UUID;

import kr.mywork.domain.dashboard.service.dto.response.DashboardCountSummaryResponse;
import kr.mywork.domain.project.model.ProjectAssign;
import kr.mywork.domain.project.model.ProjectMember;

public interface DashboardRepository {
	DashboardCountSummaryResponse getAdminSummary();
	DashboardCountSummaryResponse getCompanyAdminSummary(List<UUID> projectIds);
	DashboardCountSummaryResponse getUserSummary(List<UUID> projectId);
	List<ProjectAssign> getCompanyAdminProject (UUID companyId,String userType);
	List<ProjectMember> getUserProject(UUID memberId);
}
