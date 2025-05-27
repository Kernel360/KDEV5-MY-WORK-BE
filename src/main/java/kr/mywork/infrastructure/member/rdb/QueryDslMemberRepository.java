package kr.mywork.infrastructure.member.rdb;

import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.infrastructure.company.rdb.JpaCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class QueryDslMemberRepository implements MemberRepository {

	private final JpaMemberRepository memberRepository;


}
