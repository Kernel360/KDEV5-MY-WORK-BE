package kr.mywork.domain.member.respository;

import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;

public interface MemberRepository {
    Member save(MemberCreateRequest memberCreateRequest);
}
