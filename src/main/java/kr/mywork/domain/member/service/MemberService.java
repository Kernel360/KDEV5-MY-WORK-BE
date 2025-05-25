package kr.mywork.domain.member.service;

import jakarta.transaction.Transactional;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.respository.MemberRepository;
import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(MemberCreateRequest memberCreateRequest) {

        //비밀번호셋팅 ( 입력받은 생일을 최초 생일번호로 설정)
        memberCreateRequest.setPassword(memberCreateRequest.getBirthday());

        final Member saveMember = memberRepository.save(memberCreateRequest);

        return saveMember.getId();

    }

}
