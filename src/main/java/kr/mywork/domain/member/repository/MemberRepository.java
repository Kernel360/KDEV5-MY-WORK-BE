package kr.mywork.domain.member.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.service.dto.resquest.MemberCreateRequest;

public interface MemberRepository{
    Optional<Member> findByEmailAndDeletedFalse(String email);

	List<Member> findMemberByComapnyId(UUID companyId, Pageable pageable);

	long countByCompanyIdAndDeletedFalse(UUID companyId);

    Member save(MemberCreateRequest memberCreateRequest);

	boolean existsByEmail(String email);
}
