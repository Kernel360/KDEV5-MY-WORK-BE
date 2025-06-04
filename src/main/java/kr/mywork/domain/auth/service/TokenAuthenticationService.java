package kr.mywork.domain.auth.service;

import io.jsonwebtoken.Claims;
import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.member.model.MemberRole;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.UUID;

public class TokenAuthenticationService {

	private final JwtTokenProvider jwtTokenProvider;

	public TokenAuthenticationService(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	public MemberDetails extractMemberDetailsFromAccessToken(String accessToken) {
		Claims claims = jwtTokenProvider.extractAccessTokenPayload(accessToken);

		UUID memberId = UUID.fromString(claims.get("memberId", String.class));
		String email = claims.get("email", String.class);
		String role = claims.get("role", String.class);

		MemberRole memberRole = MemberRole.of(role);

		return new MemberDetails(
			memberId,
			email,
			null,
			new SimpleGrantedAuthority(memberRole.getRoleName())
		);
	}
}
