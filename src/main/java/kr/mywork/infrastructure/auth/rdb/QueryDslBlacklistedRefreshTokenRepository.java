package kr.mywork.infrastructure.auth.rdb;


import static kr.mywork.domain.auth.model.QBlacklistedRefreshToken.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.auth.model.BlacklistedRefreshToken;
import kr.mywork.domain.auth.repository.BlacklistedRefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class QueryDslBlacklistedRefreshTokenRepository implements BlacklistedRefreshTokenRepository {

	private final JpaBlacklistedRefreshTokenRepository blacklistedRefreshTokenRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public BlacklistedRefreshToken save(final BlacklistedRefreshToken token) {
		return blacklistedRefreshTokenRepository.save(token);
	}

	@Override
	public Optional<BlacklistedRefreshToken> findByToken(final String token) {
		return blacklistedRefreshTokenRepository.findByToken(token);
	}

	@Override
	public boolean existsByToken(final String token) {
		return blacklistedRefreshTokenRepository.existsByToken(token);
	}

	@Override
	public void deleteExpiredTokens() {
		queryFactory.delete(blacklistedRefreshToken)
			.where(blacklistedRefreshToken.expiresAt.before(LocalDateTime.now()))
			.execute();
	}
}
