package kr.mywork.domain.member.model;

import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberTypeNotFoundException;


import java.util.Arrays;

public enum MemberType {
    DEV,CLIENT,ADMIN;

    public static MemberType from(String value) {
        return Arrays.stream(MemberType.values())
                .filter(companyType -> companyType.name().equals(value))
                .findAny()
                .orElseThrow(() -> new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND));
    }
}
