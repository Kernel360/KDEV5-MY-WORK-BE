package kr.mywork.domain.auth.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenResponse {
	private final String accessToken;
	private final LocalDateTime expiresAt;
}
