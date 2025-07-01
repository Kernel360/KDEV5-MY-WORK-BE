package kr.mywork.domain.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import kr.mywork.common.auth.config.JwtProperties;
import kr.mywork.domain.auth.errors.InvalidTokenException;
import kr.mywork.domain.auth.errors.TokenExpiredException;
import kr.mywork.domain.auth.errors.AuthErrorType;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtTokenProvider {

	private final SecretKey accessTokenPrivateKey;
	private final SecretKey refreshTokenPrivateKey;
	private final Long accessTokenExpirationMillis;
	private final Long refreshTokenExpirationMillis;

	private final JwtParser accessTokenParser;
	private final JwtParser refreshTokenParser;

	public JwtTokenProvider(JwtProperties jwtProperties) {
		this.accessTokenPrivateKey = transformSecretKey(jwtProperties.getAccessToken().getPrivateKey());
		this.refreshTokenPrivateKey = transformSecretKey(jwtProperties.getRefreshToken().getPrivateKey());
		this.accessTokenExpirationMillis = jwtProperties.getAccessToken().getExpiration();
		this.refreshTokenExpirationMillis = jwtProperties.getRefreshToken().getExpiration();
		this.accessTokenParser = createJwtParser(this.accessTokenPrivateKey);
		this.refreshTokenParser = createJwtParser(this.refreshTokenPrivateKey);
	}

	public long getRefreshTokenExpirationMillis() {
		return refreshTokenExpirationMillis;
	}

	private JwtParser createJwtParser(final SecretKey secretKey) {
		return Jwts.parser().verifyWith(secretKey).build();
	}

	private SecretKey transformSecretKey(final String secretKey) {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	public String createAccessToken(final UUID memberId, final String email, final String role, final String name,
		final UUID companyId, final String companyName, final String companyType,final String logoImagePath) {

		final Date now = new Date();
		final Date expiry = new Date(now.getTime() + accessTokenExpirationMillis);

		Map<String, Object> claims = new HashMap<>();
		claims.put("memberId", memberId);
		claims.put("email", email);
		claims.put("role", role);
		claims.put("name", name);
		claims.put("companyId", companyId);
		claims.put("companyName", companyName);
		claims.put("logoImagePath", logoImagePath);
		claims.put("companyType", companyType);


		return Jwts.builder()
			.subject(String.valueOf(memberId))
			.claims(claims)
			.issuedAt(now)
			.expiration(expiry)
			.signWith(accessTokenPrivateKey)
			.compact();
	}

	public String createRefreshToken(final UUID memberId, final String email, final String role, final UUID companyId, final String companyName, final String companyType,final String logoImagePath) {
		final Date now = new Date();
		final Date expiry = new Date(now.getTime() + refreshTokenExpirationMillis);

		Map<String, Object> claims = new HashMap<>();
		claims.put("memberId", memberId);
		claims.put("email", email);
		claims.put("role", role);
		claims.put("companyId", companyId);
		claims.put("companyName", companyName);
		claims.put("logoImagePath", logoImagePath);
		claims.put("companyType", companyType);

		return Jwts.builder()
			.subject(String.valueOf(memberId))
			.claims(claims)
			.issuedAt(now)
			.expiration(expiry)
			.signWith(refreshTokenPrivateKey)
			.compact();
	}

	public boolean validateAccessToken(final String token) {
		try {
			accessTokenParser.parseSignedClaims(token);
			return true;
		} catch (ExpiredJwtException e) {
			throw new TokenExpiredException(AuthErrorType.ACCESS_TOKEN_EXPIRED);
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidTokenException(AuthErrorType.INVALID_ACCESS_TOKEN);
		}
	}

	public boolean validateRefreshToken(final String token) {
		try {
			refreshTokenParser.parseSignedClaims(token);
			return true;
		} catch (ExpiredJwtException e) {
			throw new TokenExpiredException(AuthErrorType.ACCESS_TOKEN_EXPIRED);
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidTokenException(AuthErrorType.INVALID_ACCESS_TOKEN);
		}
	}

	public Claims extractAccessTokenPayload(final String accessToken) {
		try {
			return accessTokenParser.parseSignedClaims(accessToken).getPayload();
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidTokenException(AuthErrorType.INVALID_ACCESS_TOKEN);
		}
	}

	public Claims extractRefreshTokenPayload(final String refreshToken) {
		try {
			return refreshTokenParser.parseSignedClaims(refreshToken).getPayload();
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidTokenException(AuthErrorType.INVALID_REFRESH_TOKEN);
		}
	}
	public String resolveAccessToken(String authorizationHeader) {
		final String BEARER_PREFIX = "Bearer ";
		if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
			return authorizationHeader.substring(BEARER_PREFIX.length());
		}
		return null;
	}

	public String resolveRefreshToken(HttpServletRequest request) {
		if (request.getCookies() == null) {
			return null;
		}

		for (Cookie cookie : request.getCookies()) {
			if ("refreshToken".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

	public LocalDateTime extractExpiration(String refreshToken) {
		Date expiration = extractRefreshTokenPayload(refreshToken).getExpiration();
		return expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public String extractSubject(String refreshToken) {
		return extractRefreshTokenPayload(refreshToken).getSubject();
	}
}
