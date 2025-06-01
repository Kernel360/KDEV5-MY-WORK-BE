package kr.mywork.infrastructure.member.rdb;

import kr.mywork.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaMemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmailAndDeletedFalse(String email);
}
