package kr.mywork.domain.project_step.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.project_step.model.ProjectStep;

public interface ProjectStepRepository {
	List<ProjectStep> saveAll(List<ProjectStep> projectSteps);
	List<ProjectStep> findAllByIds(Collection<UUID> projectStepIds);
	List<ProjectStep> findAllStepsByProjectIdOrderByNumAsc(UUID projectId);
	Optional<ProjectStep> findById(UUID projectStepId);
	List<ProjectStep> findAllByProjectId(UUID projectId);
}
