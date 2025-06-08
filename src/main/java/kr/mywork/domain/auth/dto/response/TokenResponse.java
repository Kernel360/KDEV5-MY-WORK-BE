package kr.mywork.domain.auth.dto.response;

import java.time.LocalDateTime;

import java.util.UUID;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenResponse {
	private final String accessToken;
	private final LocalDateTime expiresAt;

	private final UUID memberId;
	private final String memberName;
	private final String memberRole;
	private final UUID companyId;
	private final String companyName;
	private final String logoImagePath;
	private final String companyType;


}
