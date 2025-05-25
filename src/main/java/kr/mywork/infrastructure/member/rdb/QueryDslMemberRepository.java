package kr.mywork.infrastructure.member.rdb;

import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.respository.MemberRepository;
import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueryDslMemberRepository implements MemberRepository {
    private final JpaMemberRepository memberRepository;

    @Override
    public Member save(final MemberCreateRequest memberCreateRequest) {
        return memberRepository.save(memberCreateRequest.toEntity());
    }
}
