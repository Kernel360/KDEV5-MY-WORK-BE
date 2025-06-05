package kr.mywork.infrastructure.member.rdb;

import static kr.mywork.domain.member.model.QMember.member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.resquest.MemberCreateRequest;
import kr.mywork.domain.project.model.QProjectMember;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslMemberRepository implements MemberRepository {

	private final JpaMemberRepository memberRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Member> findByEmailAndDeletedFalse(String email) {
		return memberRepository.findByEmailAndDeletedFalse(email);
	}

	@Override
	public List<Member> findMemberByCompanyId(UUID companyId, int page, int memberPageSize) {
		final int offset = (page - 1) * memberPageSize;

		return queryFactory
			.selectFrom(member)
			.where(
				member.companyId.eq(companyId),
				member.deleted.eq(false))
			.orderBy(member.name.asc())
			.offset(offset)
			.limit(memberPageSize)
			.fetch();
	}

	@Override
	public long countByCompanyIdAndDeletedFalse(UUID companyId) {
		return memberRepository.countByCompanyIdAndDeletedFalse(companyId);
	}

	@Override
	public Member save(final Member member) {
		return memberRepository.save(member);
	}

	@Override
	public boolean existsByEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	@Override
	public Optional<Member> findById(UUID memberId) {
		return memberRepository.findById(memberId);
	}

	@Override
	public List<Member> findMemberListByCompanyId(UUID companyId,UUID projectId) {
		QProjectMember projectMember = QProjectMember.projectMember;

		return queryFactory
			.selectFrom(member)
			.where(
				member.companyId.eq(companyId),
				member.deleted.eq(false),
				//서브쿼리 : 프로젝트 멤버 테이블에  없는 유저
				queryFactory
					.selectOne()
					.from(projectMember)
					.where(
						projectMember.projectId.eq(projectId),
						projectMember.memberId.eq(member.id),
						projectMember.deleted.eq(false)
					)
					.notExists()
			)
			.fetch();
	}
}
