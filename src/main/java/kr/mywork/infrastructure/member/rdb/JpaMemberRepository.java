package kr.mywork.infrastructure.member.rdb;

import kr.mywork.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
}
