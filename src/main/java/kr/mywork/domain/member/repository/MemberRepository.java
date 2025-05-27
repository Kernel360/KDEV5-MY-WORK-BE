package kr.mywork.domain.member.repository;

import kr.mywork.domain.member.model.Member;

import java.util.List;
import java.util.UUID;

public interface MemberRepository {
    List<Member> findAllMemberByCompanyId(UUID companyId);
}
