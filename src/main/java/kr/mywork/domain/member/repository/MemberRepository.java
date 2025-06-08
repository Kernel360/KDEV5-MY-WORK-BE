package kr.mywork.domain.member.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.company.service.dto.response.MemberDetailResponse;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.service.dto.response.MemberSelectResponse;

public interface MemberRepository {
	Optional<Member> findByEmailAndDeletedFalse(String email);

	List<Member> findMemberByCompanyId(UUID companyId, int page, int memberPageSize);

	long countByCompanyIdAndDeletedFalse(UUID companyId);

	Member save(Member member);

	boolean existsByEmail(String email);

	Optional<Member> findById(UUID memberId);

	Long countTotalmembersByCondition(String keyword, String keywordTyp,UUID companyId);

	List<MemberSelectResponse> findMembersBySearchWithPaging(int page,int memberPageSize, String keyword,String keywordTyp,UUID companyId);

	List<Member> findMemberListByCompanyId(UUID companyId, UUID projectId);

	MemberDetailResponse findMemberDetailByMemberId(UUID memberId);
}
