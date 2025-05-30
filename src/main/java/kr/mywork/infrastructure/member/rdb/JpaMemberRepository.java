package kr.mywork.infrastructure.member.rdb;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.member.model.Member;

public interface JpaMemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmailAndDeletedFalse(String email);
	long countByCompanyIdAndDeletedFalse(UUID companyId);

	boolean existsByEmail(String email);
}
