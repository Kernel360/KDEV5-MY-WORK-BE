package kr.mywork.domain.member.model;

import java.util.Arrays;

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
}