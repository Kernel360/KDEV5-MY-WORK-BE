package kr.mywork.infrastructure.member.rdb;

import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QueryDslMemberRepository implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public Optional<Member> findByEmailAndDeletedFalse(String email) {
        return jpaMemberRepository.findByEmailAndDeletedFalse(email);
    }


}
