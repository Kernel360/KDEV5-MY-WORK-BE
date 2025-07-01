package kr.mywork.domain.post.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.post.listener.event.PostReviewsDeleteEvent;
import kr.mywork.domain.post.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostReviewsDeleteTxListener {

	private final ReviewService reviewService;

	@Async("eventTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePostReviews (final PostReviewsDeleteEvent event) {
		final Long deletedReviewsCount = reviewService.deletePostReviews(event.postId());
		log.info("post reviews deleted count : {}, postId : {}", deletedReviewsCount, event.postId());
	}
}
