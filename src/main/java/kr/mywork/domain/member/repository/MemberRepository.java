package kr.mywork.domain.member.repository;

import java.util.List;
import java.util.UUID;

import kr.mywork.domain.member.model.Member;

public interface MemberRepository {

	List<Member> findMemberByComapnyId(UUID companyId,long offset);

	long countByCompanyIdAndDeletedFalse(UUID companyId);
}
