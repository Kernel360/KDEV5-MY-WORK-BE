package kr.mywork.infrastructure.member.rdb;

import static kr.mywork.domain.member.model.QMember.member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.member.service.dto.resquest.MemberCreateRequest;
import org.springframework.data.domain.Pageable;
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
	public List<Member> findMemberByComapnyId(UUID companyId, Pageable pageable) {
		return queryFactory
			.selectFrom(member)
			.where(
				member.companyId.eq(companyId),
				member.deleted.eq(false)
			)
			.orderBy(member.name.asc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
	}
	@Override
	public long countByCompanyIdAndDeletedFalse(UUID companyId) {
		return memberRepository.countByCompanyIdAndDeletedFalse(companyId);
	}

	@Override
	public Member save(final MemberCreateRequest memberCreateRequest) {
		return memberRepository.save(memberCreateRequest.toEntity());
	}

	@Override
	public boolean existsByEmail(String email) {
		return memberRepository.existsByEmail(email);
	}


}
