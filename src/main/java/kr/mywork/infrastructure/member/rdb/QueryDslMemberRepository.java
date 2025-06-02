package kr.mywork.infrastructure.member.rdb;

import static kr.mywork.domain.member.model.QMember.member;

import java.util.List;
import java.util.Optional;
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
				member.deleted.eq(false)
			)
			.orderBy(member.name.asc())
			.offset(offset)
			.limit(memberPageSize)
			.fetch();
	}
	@Override
	public long countByCompanyIdAndDeletedFalse(UUID companyId) {
		return memberRepository.countByCompanyIdAndDeletedFalse(companyId);
	}
}
