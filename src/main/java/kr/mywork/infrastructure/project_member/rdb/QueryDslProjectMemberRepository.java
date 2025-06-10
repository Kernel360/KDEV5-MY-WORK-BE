package kr.mywork.infrastructure.project_member.rdb;

import static kr.mywork.domain.member.model.QMember.member;
import static kr.mywork.domain.project.model.QProjectMember.projectMember;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;
import kr.mywork.domain.project_member.service.dto.response.CompanyMemberInProjectResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslProjectMemberRepository implements ProjectMemberRepository {

	final JpaProjectMemberRepository jpaProjectMemberRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public ProjectMember save(ProjectMember projectMember) {
		return jpaProjectMemberRepository.save(projectMember);
	}

	@Override
	public List<CompanyMemberInProjectResponse> findCompanyMembersInProject(UUID projectId, UUID companyId) {
		return queryFactory
			.select(Projections.constructor(
				CompanyMemberInProjectResponse.class,
				member.id,
				member.name,
				member.email
			))
			.from(projectMember)
			.join(member).on(projectMember.memberId.eq(member.id))
			.where(
				projectMember.projectId.eq(projectId),
				projectMember.deleted.eq(false),
				member.companyId.eq(companyId),
				member.deleted.eq(false)
			)
			.fetch();
	}

	@Override
	public Optional<ProjectMember> findByMemberIdAndProjectId(UUID memberId,UUID projectId) {
		return jpaProjectMemberRepository.findByMemberIdAndProjectId(memberId,projectId);
	}

}
