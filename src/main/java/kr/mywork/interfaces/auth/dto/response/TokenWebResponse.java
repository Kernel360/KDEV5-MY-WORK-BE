package kr.mywork.interfaces.auth.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;


import kr.mywork.domain.auth.dto.response.TokenResponse;
import lombok.Getter;

@Getter
public class TokenWebResponse {


	private final String accessToken;
	private final LocalDateTime expiresAt;

	private final UUID memberId;
	private final String memberName;
	private final String memberRole;

	public TokenWebResponse(
		String accessToken,
		LocalDateTime expiresAt,
		UUID memberId,
		String memberName,
		String memberRole
	) {
		this.accessToken = accessToken;
		this.expiresAt = expiresAt;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberRole = memberRole;
	}

	public static TokenWebResponse from(TokenResponse tokenResponse) {
		return new TokenWebResponse(
			tokenResponse.getAccessToken(),
			tokenResponse.getExpiresAt(),
			tokenResponse.getMemberId(),
			tokenResponse.getMemberName(),
			tokenResponse.getMemberRole()
		);
	}
}
