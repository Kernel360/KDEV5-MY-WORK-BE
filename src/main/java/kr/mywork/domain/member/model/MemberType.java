package kr.mywork.domain.member.model;

import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberTypeNotFoundException;

import java.util.Arrays;

public enum MemberType {
	USER, DEV_ADMIN, CLIENT_ADMIN, SYSTEM_ADMIN, ANONYMOUS;

	public static MemberType from(String value) {
		return Arrays.stream(MemberType.values())
			.filter(memberType -> memberType.name().equals(value))
			.findAny()
			.orElseThrow(() -> new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND));
	}
}
