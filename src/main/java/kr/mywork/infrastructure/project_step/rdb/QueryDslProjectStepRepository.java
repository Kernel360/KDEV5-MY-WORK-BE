package kr.mywork.infrastructure.project_step.rdb;

import static kr.mywork.domain.project_step.model.QProjectStep.projectStep;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.project_step.model.ProjectStep;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslProjectStepRepository implements ProjectStepRepository {

	private final JpaProjectStepRepository jpaProjectStepRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public List<ProjectStep> saveAll(final List<ProjectStep> projectSteps) {
		return jpaProjectStepRepository.saveAll(projectSteps);
	}

	@Override
	public List<ProjectStep> findAllByIds(final Set<UUID> projectStepIds) {
		return queryFactory.selectFrom(projectStep)
			.where(projectStep.id.in(projectStepIds))
			.fetch();
	}
}
