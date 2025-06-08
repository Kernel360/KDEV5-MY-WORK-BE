package kr.mywork.domain.auth.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.domain.auth.dto.response.TokenResponse;
import kr.mywork.domain.auth.errors.AuthErrorType;
import kr.mywork.domain.auth.errors.AuthException;
import kr.mywork.domain.auth.model.BlacklistedRefreshToken;
import kr.mywork.domain.auth.repository.BlacklistedRefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtTokenProvider jwtTokenProvider;
	private final BlacklistedRefreshTokenRepository blacklistedRefreshTokenRepository;

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		String refreshToken = resolveRefreshTokenFromCookie(request);

		if (refreshToken != null && jwtTokenProvider.validateRefreshToken(refreshToken)) {
			LocalDateTime expiresAt = jwtTokenProvider.extractExpiration(refreshToken);
			blacklistedRefreshTokenRepository.save(new BlacklistedRefreshToken(refreshToken, expiresAt));
		}

		clearRefreshTokenCookie(response);
	}

	public TokenResponse reissue(HttpServletRequest request, HttpServletResponse response) {
		String refreshToken = resolveRefreshTokenFromCookie(request);

		if (refreshToken == null) {
			throw new AuthException(AuthErrorType.REFRESH_TOKEN_NOT_FOUND);
		}

		if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
			throw new AuthException(AuthErrorType.INVALID_TOKEN);
		}

		if (isRefreshTokenBlacklisted(refreshToken)) {
			throw new AuthException(AuthErrorType.INVALID_TOKEN);
		}

		Claims claims = jwtTokenProvider.extractRefreshTokenPayload(refreshToken);

		UUID memberId = UUID.fromString(claims.getSubject());
		String email = claims.get("email", String.class);
		String role = claims.get("role", String.class);
		String name = claims.get("name", String.class);
		UUID companyId = UUID.fromString(claims.get("companyId", String.class));
		String companyName = claims.get("companyName", String.class);
		String logoImagePath = claims.get("logoImagePath", String.class);
		String companyType = claims.get("companyType", String.class);


		String newAccessToken = jwtTokenProvider.createAccessToken(memberId, email, role, name,companyId,companyName,logoImagePath,companyType);
		LocalDateTime expiresAt = jwtTokenProvider.extractExpiration(refreshToken);

		return new TokenResponse(newAccessToken, expiresAt, memberId, role, name,companyId,companyName,logoImagePath,companyType);

	}

	private String resolveRefreshTokenFromCookie(HttpServletRequest request) {
		if (request.getCookies() == null) return null;
		for (Cookie cookie : request.getCookies()) {
			if ("refreshToken".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

	private void clearRefreshTokenCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	private boolean isRefreshTokenBlacklisted(String token) {
		return blacklistedRefreshTokenRepository.existsByToken(token);
	}
}
