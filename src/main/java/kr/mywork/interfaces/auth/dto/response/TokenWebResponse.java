package kr.mywork.interfaces.auth.dto.response;

import java.util.UUID;

import kr.mywork.domain.auth.dto.response.TokenResponse;

public record TokenWebResponse(String accessToken, UUID memberId, String memberName, String memberRole, UUID companyId,
							   String companyName, String logoImagePath, String companyType) {

	public static TokenWebResponse from(TokenResponse tokenResponse) {
		return new TokenWebResponse(
			tokenResponse.getAccessToken(),
			tokenResponse.getMemberId(),
			tokenResponse.getMemberName(),
			tokenResponse.getMemberRole(),
			tokenResponse.getCompanyId(),
			tokenResponse.getCompanyName(),
			tokenResponse.getLogoImagePath(),
			tokenResponse.getCompanyType()
		);
	}
}
