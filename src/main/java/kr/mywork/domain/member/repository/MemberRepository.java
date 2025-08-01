package kr.mywork.domain.member.repository;

import kr.mywork.domain.company.service.dto.response.MemberDetailResponse;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.service.dto.response.MemberSelectResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
	Optional<Member> findByEmailAndDeletedFalse(String email);

	List<Member> findMemberByCompanyId(UUID companyId, int page, int memberPageSize, String memberName);

	long countByCompanyIdAndDeletedFalse(UUID companyId,String memberName);

	Member save(Member member);

	boolean existsByEmail(String email);

	Optional<Member> findById(UUID memberId);

	Long countTotalmembersByCondition(String keyword, String keywordTyp,UUID companyId);

	List<MemberSelectResponse> findMembersBySearchWithPaging(int page,int memberPageSize, String keyword,String keywordTyp,UUID companyId);

	List<Member> findMemberListByCompanyId(UUID companyId, UUID projectId);

	MemberDetailResponse findMemberDetailByMemberId(UUID memberId);

	Optional<Member> findFirstByEmail(String email);
}
