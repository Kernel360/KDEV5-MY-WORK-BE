package kr.mywork.domain.member.model;

import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberTypeNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
	ANONYMOUS("ROLE_ANONYMOUS"),
	USER("ROLE_USER"),
	DEV_ADMIN("ROLE_DEV_ADMIN"),
	CLIENT_ADMIN("ROLE_CLIENT_ADMIN"),
	SYSTEM_ADMIN("ROLE_SYSTEM_ADMIN");

	private final String roleName;

	public static MemberRole fromRoleName(final String inputRoleName) {
		return Arrays.stream(values())
			.filter(role -> role.getRoleName().equalsIgnoreCase(inputRoleName))
			.findFirst()
			.orElseThrow(() -> new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND));
	}

	public static MemberRole fromName(final String name) {
		return Arrays.stream(MemberRole.values())
			.filter(memberRole -> memberRole.name().equals(name))
			.findAny()
			.orElseThrow(() -> new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND));
	}

	public boolean isSameRoleName(final String roleName) {
		return this.roleName.equals(roleName);
	}
}
