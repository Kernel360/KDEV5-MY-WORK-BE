package kr.mywork.domain.member.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.member.model.Member;

public interface MemberRepository{
    Optional<Member> findByEmailAndDeletedFalse(String email);

	List<Member> findMemberByCompanyId(UUID companyId, int page);

	long countByCompanyIdAndDeletedFalse(UUID companyId);
}
