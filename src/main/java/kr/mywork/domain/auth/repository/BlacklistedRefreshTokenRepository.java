package kr.mywork.domain.auth.repository;

import java.util.Optional;

import kr.mywork.domain.auth.model.BlacklistedRefreshToken;

public interface BlacklistedRefreshTokenRepository {
	BlacklistedRefreshToken save(BlacklistedRefreshToken token);

	Optional<BlacklistedRefreshToken> findByToken(String token);

	boolean existsByToken(String token);

	void deleteExpiredTokens();
}
