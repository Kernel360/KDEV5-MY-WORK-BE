package kr.mywork.infrastructure.dashboard.rdb;

import static kr.mywork.domain.project.model.QProject.*;
import static kr.mywork.domain.project.model.QProjectAssign.*;
import static kr.mywork.domain.project.model.QProjectMember.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.dashboard.service.dto.response.DashboardCountSummaryResponse;
import kr.mywork.domain.dashboard.service.errors.DashboardErrorType;
import kr.mywork.domain.dashboard.service.errors.DashboardException;
import kr.mywork.domain.dashboard.service.repository.DashboardRepository;
import kr.mywork.domain.project.model.ProjectAssign;
import kr.mywork.domain.project.model.ProjectMember;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class QueryDslDashboardRepository implements DashboardRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public DashboardCountSummaryResponse getAdminSummary() {
		LocalDateTime now = LocalDateTime.now();

		Tuple result = queryFactory.
			select(
				project.count(),
				new CaseBuilder()
					.when(project.startAt.before(now).and(project.endAt.after(now)))
					.then(1L).otherwise(0L).sum()
					.castToNum(Long.class),
				new CaseBuilder()
					.when(project.endAt.before(now))
					.then(1L).otherwise(0L).sum()
					.castToNum(Long.class)
			)
			.from(project)
			.where(project.deleted.isFalse())
			.fetchOne();

		Long total = Optional.ofNullable(result.get(0, Long.class)).orElse(0L);
		Long inProgress = Optional.ofNullable(result.get(1, Long.class)).orElse(0L);
		Long completed = Optional.ofNullable(result.get(2, Long.class)).orElse(0L);

		return new DashboardCountSummaryResponse(total, inProgress, completed);
	}

	@Override
	public DashboardCountSummaryResponse getCompanyAdminSummary(List<UUID> projectIds) {
		LocalDateTime now = LocalDateTime.now();

		Tuple result = queryFactory
			.select(
				project.count(),
				new CaseBuilder()
					.when(project.startAt.before(now).and(project.endAt.after(now)))
					.then(1L).otherwise(0L).sum()
					.castToNum(Long.class),
				new CaseBuilder()
					.when(project.endAt.before(now))
					.then(1L).otherwise(0L).sum()
					.castToNum(Long.class)
			)
			.from(project)
			.where(
				project.id.in(projectIds),
				project.deleted.isFalse()
			)
			.fetchOne();

		Long total = Optional.ofNullable(result.get(0, Long.class)).orElse(0L);
		Long inProgress = Optional.ofNullable(result.get(1, Long.class)).orElse(0L);
		Long completed = Optional.ofNullable(result.get(2, Long.class)).orElse(0L);
		return new DashboardCountSummaryResponse(total, inProgress, completed);
	}

	@Override
	public DashboardCountSummaryResponse getUserSummary(List<UUID> projectIds) {
		LocalDateTime now = LocalDateTime.now();

		Tuple result = queryFactory
			.select(
				project.count(),
				new CaseBuilder()
					.when(project.startAt.before(now).and(project.endAt.after(now)))
					.then(1L).otherwise(0L).sum()
					.castToNum(Long.class),
				new CaseBuilder()
					.when(project.endAt.before(now))
					.then(1L).otherwise(0L).sum()
					.castToNum(Long.class)
			)
			.from(project)
			.where(
				project.id.in(projectIds),
				project.deleted.isFalse()
			)
			.fetchOne();

		Long total = Optional.ofNullable(result.get(0, Long.class)).orElse(0L);
		Long inProgress = Optional.ofNullable(result.get(1, Long.class)).orElse(0L);
		Long completed = Optional.ofNullable(result.get(2, Long.class)).orElse(0L);
		return new DashboardCountSummaryResponse(total, inProgress, completed);
	}

	@Override
	public List<ProjectAssign> getCompanyAdminProject(UUID companyId, String userType) {
		if("dev".equals(userType)) {
			return getDevCompanyProjects(companyId);
		}else if ("client".equals(userType)) {
			return getClientCompanyProjects(companyId);
		} else {
			throw new DashboardException(DashboardErrorType.TYPE_NOT_FOUND);
		}
	}

	@Override
	public List<ProjectMember> getUserProject(UUID memberId) {
		return queryFactory
			.selectFrom(projectMember)
			.where(projectMember.memberId.eq(memberId))
			.fetch();
	}

	private List<ProjectAssign> getDevCompanyProjects(UUID devCompanyId) {
		return queryFactory
			.selectFrom(projectAssign)
			.where(projectAssign.devCompanyId.eq(devCompanyId))
			.fetch();
	}
	private List<ProjectAssign> getClientCompanyProjects(UUID clientCompanyId) {
		return queryFactory
			.selectFrom(projectAssign)
			.where(projectAssign.clientCompanyId.eq(clientCompanyId))
			.fetch();
	}
}
