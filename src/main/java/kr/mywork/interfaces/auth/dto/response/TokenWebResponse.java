package kr.mywork.interfaces.auth.dto.response;

import java.time.LocalDateTime;

import kr.mywork.domain.auth.dto.response.TokenResponse;
import lombok.Getter;

@Getter
public class TokenWebResponse {
	private final String accessToken;
	private final LocalDateTime expiresAt;

	public TokenWebResponse(String accessToken, LocalDateTime expiresAt) {
		this.accessToken = accessToken;
		this.expiresAt = expiresAt;
	}

	public static TokenWebResponse from(TokenResponse tokenResponse) {
		return new TokenWebResponse(tokenResponse.getAccessToken(), tokenResponse.getExpiresAt());
	}
}
