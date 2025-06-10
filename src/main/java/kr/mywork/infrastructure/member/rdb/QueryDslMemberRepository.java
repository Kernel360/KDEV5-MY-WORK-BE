package kr.mywork.infrastructure.member.rdb;

import static kr.mywork.domain.company.model.QCompany.company;
import static kr.mywork.domain.member.model.QMember.member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.company.service.dto.response.MemberDetailResponse;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.response.MemberSelectResponse;
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

	@Override
	public Long countTotalmembersByCondition(final String keyword, final String keywordType,final UUID companyId) {
		BooleanBuilder builder = new BooleanBuilder();

		builder.and(member.deleted.eq(false));
		if (companyId != null) {builder.and(member.companyId.eq(companyId));}

		if (keyword != null && keywordType != null) {
			switch (keywordType) {
				case "NAME" -> builder.and(member.name.containsIgnoreCase(keyword));
				case "EMAIL" -> builder.and(member.email.containsIgnoreCase(keyword));
				case "POSITION" -> builder.and(member.position.containsIgnoreCase(keyword));
				case "DEPARTMENT" -> builder.and(member.department.containsIgnoreCase(keyword));
				case "PHONENUMBER" -> builder.and(member.phoneNumber.containsIgnoreCase(keyword));
			};
		}

		return queryFactory.select(member.id.count())
			.from(member)
			.leftJoin(company).on(member.companyId.eq(company.id))
			.where(builder)
			.fetchOne();
	}

	@Override
	public List<MemberSelectResponse> findMembersBySearchWithPaging(int page, int memberPageSize, String keyword,
		String keywordType, UUID companyId) {
		BooleanBuilder builder = new BooleanBuilder();
		final int offset = (page - 1) * memberPageSize;



		builder.and(member.deleted.eq(false));
		if (companyId != null) {builder.and(member.companyId.eq(companyId));}

		if (keyword != null && keywordType != null) {
			switch (keywordType) {
				case "NAME" -> builder.and(member.name.containsIgnoreCase(keyword));
				case "EMAIL" -> builder.and(member.email.containsIgnoreCase(keyword));
				case "POSITION" -> builder.and(member.position.containsIgnoreCase(keyword));
				case "DEPARTMENT" -> builder.and(member.department.containsIgnoreCase(keyword));
				case "PHONENUMBER" -> builder.and(member.phoneNumber.containsIgnoreCase(keyword));
			}
			;
		}

		return queryFactory.select(Projections.constructor(MemberSelectResponse.class,
				member.id,
				member.name,
				member.email,
				member.position,
				member.department,
				member.phoneNumber,
				member.deleted,
				member.createdAt,
				member.companyId,
				company.name
			))
			.from(member)
			.leftJoin(company).on(member.companyId.eq(company.id))
			.where(builder)
			.offset(offset)
			.limit(memberPageSize)
			.fetch();
	}

	@Override
	public MemberDetailResponse findMemberDetailByMemberId(UUID memberId) {
		return queryFactory.select(Projections.constructor(
			MemberDetailResponse.class,
			member.companyId,
			company.name,
			member.name,
			member.department,
			member.position,
			member.role.stringValue(),
			member.phoneNumber,
			member.email,
			member.deleted,
			member.modifiedAt,
			member.createdAt,
			company.contactPhoneNumber,
			member.birthDate
			))
			.from(member)
			.join(company).on(member.companyId.eq(company.id))
			.where(
				member.id.eq(memberId),
				member.deleted.eq(false),
				company.deleted.eq(false)
			)
			.fetchOne();
	}
	@Override
	public Optional<Member> findFirstByEmail(String email){
		return memberRepository.findFirstByEmail(email);
	}
}
