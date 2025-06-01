package kr.mywork.infrastructure.project_step.rdb;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.project_step.model.ProjectStep;

public interface JpaProjectStepRepository extends JpaRepository<ProjectStep, UUID> {
}
