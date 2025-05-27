package kr.mywork.domain.member.service;

import jakarta.transaction.Transactional;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public List<Member> findAllByCompanyId(UUID companyId) {
        List<Member> members = memberRepository.findAllMemberByCompanyId(companyId);
        return members;
    }
}
