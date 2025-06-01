package kr.mywork.domain.member.repository;

import kr.mywork.domain.member.model.Member;

import java.util.Optional;

public interface MemberRepository{
    Optional<Member> findByEmailAndDeletedFalse(String email);
}
