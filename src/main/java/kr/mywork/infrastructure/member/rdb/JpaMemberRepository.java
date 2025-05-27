package kr.mywork.infrastructure.member.rdb;

import kr.mywork.domain.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.UUID;

public interface JpaMemberRepository extends JpaRepository<Member, UUID> {
}
