package kr.mywork.domain.project.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.member.service.dto.response.MemberProjectInfoResponse;
import kr.mywork.domain.project.model.Project;
import kr.mywork.domain.project.service.dto.response.DashboardMostPostProjectResponse;

public interface ProjectRepository {

	Project save(Project project);

	Optional<Project> findById(UUID projectId);

	Long countTotalProjectIdsAndStep(Collection<UUID> projectIds, String step);

	List<MemberProjectInfoResponse> findeMemberProjectList(UUID memberId);

	List<Project> findAllByStepAndNameWithPaging(String step, String title, Integer page, Integer size);

	List<Project> findAllByIdsAndStep(Collection<UUID> projectIds, String step, Integer page, Integer projectPageSize);

	Long countTotalProjectsByNameAndStep(String keyword, String step);

	List<Project> findPopularProjectsName(List<DashboardMostPostProjectResponse> mostPostProjectIds);


}
