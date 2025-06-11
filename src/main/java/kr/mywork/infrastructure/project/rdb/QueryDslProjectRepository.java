package kr.mywork.infrastructure.project.rdb;

import static kr.mywork.domain.project.model.QProject.project;
import static kr.mywork.domain.project.model.QProjectAssign.projectAssign;
import static kr.mywork.domain.project.model.QProjectMember.projectMember;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.member.service.dto.response.MemberProjectInfoResponse;
import kr.mywork.domain.project.model.Project;
import kr.mywork.domain.project.repository.ProjectRepository;
import kr.mywork.domain.project.service.dto.response.ProjectSelectWithAssignResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class QueryDslProjectRepository implements ProjectRepository {

	private final JpaProjectRepository projectRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public Project save(final Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Optional<Project> findById(UUID projectId) {
		return projectRepository.findById(projectId);
	}

	@Override
	public List<ProjectSelectWithAssignResponse> findProjectsBySearchConditionWithPaging(
		int page,
		int size,
		UUID memberId,
		String nameKeyword,
		Boolean deleted
	) {
		int offset = (page - 1) * size;

		return queryFactory
			.select(Projections.constructor(
				ProjectSelectWithAssignResponse.class,
				project.id,
				project.name,
				project.startAt,
				project.endAt,
				project.step,
				project.detail,
				project.deleted,
				project.createdAt,
				projectAssign.devCompanyId,
				projectAssign.clientCompanyId
			))
			.from(project)
			.leftJoin(projectAssign).on(projectAssign.projectId.eq(project.id))
			.leftJoin(projectMember).on(projectMember.projectId.eq(project.id))
			.where(
				eqMember(memberId),
				eqName(nameKeyword),
				eqDeleted(deleted)
			)
			.offset(offset)
			.limit(size)
			.fetch();
	}

	@Override
	public Long countTotalProjectsByCondition(
		UUID memberId,
		String nameKeyword,
		Boolean deleted
	) {
		return queryFactory
			.select(project.id.count())
			.from(project)
			.leftJoin(projectMember).on(projectMember.projectId.eq(project.id))
			.where(
				eqMember(memberId),
				eqName(nameKeyword),
				eqDeleted(deleted)
			)
			.fetchOne();
	}

	@Override
	public List<MemberProjectInfoResponse> findeMemberProjectList(UUID memberId) {
		return queryFactory.select(Projections.constructor(
				MemberProjectInfoResponse.class,
				projectMember.projectId,
				project.name
			))
			.from(projectMember)
			.leftJoin(project).on(projectMember.projectId.eq(project.id))
			.where(
				eqMember(memberId),
				eqDeleted(false)
			)
			.fetch();
	}

	private BooleanExpression eqMember(UUID memberId) {
		if (memberId == null) {
			return null;
		}

		return projectMember.memberId.eq(memberId)
			.and(projectMember.deleted.isFalse());
	}

	private BooleanExpression eqName(String keyword) {
		if (keyword == null || keyword.isEmpty()) {
			return null;
		}
		return project.name.containsIgnoreCase(keyword);
	}

	private BooleanExpression eqDeleted(Boolean deleted) {
		if (deleted == null) {
			return null;
		}
		return project.deleted.eq(deleted);
	}
}
