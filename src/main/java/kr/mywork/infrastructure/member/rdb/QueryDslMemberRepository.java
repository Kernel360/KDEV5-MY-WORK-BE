package kr.mywork.infrastructure.member.rdb;

import static kr.mywork.domain.member.model.QMember.member;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslMemberRepository implements MemberRepository {

	private final JpaMemberRepository memberRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Member> findMemberByComapnyId(UUID companyId, long offset) {
		return queryFactory
			.selectFrom(member)
			.where(
				member.companyId.eq(companyId),
				member.deleted.eq(false)
			)
			.offset(offset)
			.limit(10)
			.fetch();
	}
	@Override
	public long countByCompanyIdAndDeletedFalse(UUID companyId) {
		return memberRepository.countByCompanyIdAndDeletedFalse(companyId);
	}
}
