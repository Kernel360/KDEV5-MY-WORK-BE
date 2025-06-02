package kr.mywork.domain.project_step.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import kr.mywork.domain.project_step.model.ProjectStep;

public interface ProjectStepRepository {
	List<ProjectStep> saveAll(List<ProjectStep> projectSteps);
	List<ProjectStep> findAllByIds(Set<UUID> projectStepIds);
}
