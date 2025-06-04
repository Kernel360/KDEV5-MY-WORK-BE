package kr.mywork.infrastructure.auth.rdb;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.auth.model.BlacklistedRefreshToken;

public interface JpaBlacklistedRefreshTokenRepository extends JpaRepository<BlacklistedRefreshToken, UUID> {
	Optional<BlacklistedRefreshToken> findByToken(String token);
	boolean existsByToken(String token);
}


