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
		String name = claims.get("name", String.class);
		MemberRole memberRole = MemberRole.of(role);
		UUID companyId = UUID.fromString(claims.get("companyId", String.class));
		String companyName = claims.get("companyName", String.class);
		String logoImagePath = claims.get("logoImagePath", String.class);
		String companyType = claims.get("companyType", String.class);


		return new MemberDetails(
			memberId,
			name,
			email,
			null,
			new SimpleGrantedAuthority(memberRole.getRoleName()),
			companyId,
			companyName,
			logoImagePath,
			companyType
		);
	}
}
