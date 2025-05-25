package kr.mywork.domain.member.errors;

import kr.mywork.domain.company.errors.CompanyErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorType {
    TYPE_NOT_FOUND(MemberErrorCode.ERROR_MEMBER01,"회원 타입을 찾을 수 없습니다.");


    private final MemberErrorCode errorCode;
    private final String message;
}
