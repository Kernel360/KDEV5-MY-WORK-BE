package kr.mywork.domain.auth.scheduler;

import kr.mywork.domain.auth.repository.BlacklistedRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteExpiredTokenScheduler {

	private final BlacklistedRefreshTokenRepository blacklistedRefreshTokenRepository;

	@Scheduled(cron = "0 0 3 * * *")
	public void cleanUpExpiredTokens() {
		blacklistedRefreshTokenRepository.deleteExpiredTokens();
	}
}
