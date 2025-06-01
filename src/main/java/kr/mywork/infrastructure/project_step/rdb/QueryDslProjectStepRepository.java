package kr.mywork.infrastructure.project_step.rdb;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.mywork.domain.project_step.model.ProjectStep;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslProjectStepRepository implements ProjectStepRepository {

	private final JpaProjectStepRepository jpaProjectStepRepository;

	@Override
	public List<ProjectStep> saveAll(final List<ProjectStep> projectSteps) {
		return jpaProjectStepRepository.saveAll(projectSteps);
	}
}
