package kr.mywork.interfaces.post.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.mywork.domain.post.service.PostIdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostIdDeleteScheduler {

	private final PostIdService postIdService;

	@Scheduled(cron = "${post.issued-id.delete.cron}")
	public void deleteIssuedPostIds() {
		final Long deletionCount = postIdService.deleteIssuedPostIds(LocalDateTime.now());
		log.info("deleted issued post-id count : {}", deletionCount);
	}
}
