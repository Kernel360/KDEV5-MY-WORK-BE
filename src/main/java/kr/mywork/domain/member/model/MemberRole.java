package kr.mywork.domain.member.model;

import java.util.Arrays;

import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberTypeNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
	ANONYMOUS("ROLE_ANONYMOUS"),
	USER("ROLE_USER"),
	DEV_ADMIN("ROLE_DEV_ADMIN"),
	CLIENT_ADMIN("ROLE_CLIENT_ADMIN"),
	SYSTEM_ADMIN("ROLE_SYSTEM_ADMIN");

	private final String roleName;

	public static MemberRole of(final String inputRole) {
		return Arrays.stream(values())
			.filter(role -> role.getRoleName().equalsIgnoreCase(inputRole))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("invalid role name: " + inputRole));
	}

	public static MemberRole from(String value) {
		return Arrays.stream(MemberRole.values())
			.filter(memberRole -> memberRole.name().equals(value))
			.findAny()
			.orElseThrow(() -> new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND));
	}
}