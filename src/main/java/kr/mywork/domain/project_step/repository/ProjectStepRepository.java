package kr.mywork.domain.project_step.repository;

import java.util.List;

import kr.mywork.domain.project_step.model.ProjectStep;

public interface ProjectStepRepository {
	List<ProjectStep> saveAll(List<ProjectStep> projectSteps);
}
