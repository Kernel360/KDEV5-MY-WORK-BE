package kr.mywork.domain.member.repository;

import java.util.List;
import java.util.UUID;

import kr.mywork.domain.member.model.Member;
import org.springframework.data.domain.Pageable;

public interface MemberRepository {

	List<Member> findMemberByComapnyId(UUID companyId, Pageable pageable);

	long countByCompanyIdAndDeletedFalse(UUID companyId);
}
