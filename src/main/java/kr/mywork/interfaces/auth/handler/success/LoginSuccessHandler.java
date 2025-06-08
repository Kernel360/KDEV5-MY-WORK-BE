package kr.mywork.interfaces.auth.handler.success;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.auth.service.JwtTokenProvider;
import kr.mywork.interfaces.auth.dto.response.TokenWebResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtTokenProvider jwtTokenProvider;
	private final ObjectMapper objectMapper;

	public LoginSuccessHandler(JwtTokenProvider jwtTokenProvider,
		ObjectMapper objectMapper) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.objectMapper = objectMapper;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication) throws IOException {

		final MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();

		final String accessToken = jwtTokenProvider.createAccessToken(
			memberDetails.getId(),
			memberDetails.getEmail(),
			memberDetails.getAuthorityAsStr(),
			memberDetails.getName(),
			memberDetails.getCompanyId(),
			memberDetails.getCompanyName(),
			memberDetails.getLogoImagePath(),
			memberDetails.getCompanyType()
		);

		final String refreshToken = jwtTokenProvider.createRefreshToken(
			memberDetails.getId(),
			memberDetails.getEmail(),
			memberDetails.getAuthorityAsStr(),
			memberDetails.getCompanyId(),
			memberDetails.getCompanyName(),
			memberDetails.getLogoImagePath(),
			memberDetails.getCompanyType()
		);

		final LocalDateTime expiresAt = jwtTokenProvider.extractExpiration(refreshToken);

		int maxAge = (int) (jwtTokenProvider.getRefreshTokenExpirationMillis() / 1000);

		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);

		TokenWebResponse tokenWebResponse = new TokenWebResponse(
			accessToken,
			expiresAt,
			memberDetails.getId(),
			memberDetails.getName(),
			memberDetails.getAuthorityAsStr(),
			memberDetails.getCompanyId(),
			memberDetails.getCompanyName(),
			memberDetails.getLogoImagePath(),
			memberDetails.getCompanyType()
		);

		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		objectMapper.writeValue(response.getWriter(), ApiResponse.success(tokenWebResponse));
	}
}
