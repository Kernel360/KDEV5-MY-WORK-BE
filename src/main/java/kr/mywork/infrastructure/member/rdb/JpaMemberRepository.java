package kr.mywork.infrastructure.member.rdb;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.member.model.Member;

public interface JpaMemberRepository extends JpaRepository<Member, UUID> {
	long countByCompanyIdAndDeletedFalse(UUID companyId);
}
