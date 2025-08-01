package kr.mywork.domain.project.repository;

import jakarta.annotation.Nullable;
import kr.mywork.domain.member.service.dto.response.MemberProjectInfoResponse;
import kr.mywork.domain.project.model.Project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {

	Project save(Project project);

	Optional<Project> findById(UUID projectId);

	Long countTotalProjectIdsAndStep(Collection<UUID> projectIds, String step);

	List<MemberProjectInfoResponse> findeMemberProjectList(UUID memberId);

	List<Project> findAllByStepAndNameWithPaging(String step, String title, Integer page, Integer size);

	List<Project> findAllByIdsAndStep(Collection<UUID> projectIds, String step, Integer page, Integer projectPageSize);

	Long countTotalProjectsByNameAndStep(String keyword, String step);

	List<Project> findAllNearDeadlineProjects(int page, int pageSize, LocalDate baseDate);

	List<Project> findAllNearDeadlineProjectsByProjectIds(Collection<UUID> projectIds, int page, int pageSize, LocalDateTime now);

	Long countNearDeadlineProjects(LocalDate baseDate);

	Long countNearDeadlineProjectsByProjectIds(Collection<UUID> projectIds, LocalDate baseDate);

	List<Project> findProjectsNameById(List<UUID> mostPostProjectIds);

	List<Project> findProjectsByIds(List<UUID> projectIds);

	List<Project> findCompletedProjectsByIdsWithDate(List<UUID> projectIds, LocalDateTime startDate,String status);

	Long getSummaryProjectTotalCount(@Nullable List<UUID> projectIds);

	Long getSummaryInProgressProjectTotalCount(@Nullable List<UUID> projectIds, LocalDateTime now);

	Long getSummaryCompletedProjectTotalCount(@Nullable List<UUID> projectIds, LocalDateTime now);

}
