package kr.mywork.infrastructure.member.rdb;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.mywork.domain.member.model.Member;

import kr.mywork.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static kr.mywork.domain.member.model.QMember.member;


@Repository
@RequiredArgsConstructor
public class QueryDslMemberRepository implements MemberRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Member> findAllMemberByCompanyId(UUID companyId) {
		List<Member> members = queryFactory
				.selectFrom(member)
				.where(
						member.companyId.eq(companyId),
						member.deleted.isFalse()
				).fetch();
		return members;
	}
}
