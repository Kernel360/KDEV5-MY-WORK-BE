package kr.mywork.domain.post.listener;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.mywork.domain.post.listener.event.PostAttachmentDeleteEvent;
import kr.mywork.domain.post.model.PostAttachment;
import kr.mywork.domain.post.repository.PostAttachmentRepository;
import kr.mywork.domain.post.uploader.PostAttachmentFileHandler;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostAttachmentFileTxListener {

	private final PostAttachmentRepository postAttachmentRepository;
	private final PostAttachmentFileHandler postAttachmentFileHandler;

	@Transactional
	@Async("eventTaskExecutor")
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void deleteAttachmentFiles(PostAttachmentDeleteEvent event) {
		final List<PostAttachment> undeletedPostAttachments =
			postAttachmentRepository.findAllByPostIdDeletedAndActive(event.postId(), false, null);

		for (PostAttachment postAttachment : undeletedPostAttachments) {
			postAttachment.delete();
		}

		final List<PostAttachment> allPostAttachments =
			postAttachmentRepository.findAllByPostIdDeletedAndActive(event.postId(), null, null);

		for (PostAttachment postAttachment : allPostAttachments) {
			postAttachmentFileHandler.delete(postAttachment.getFilePath());
		}
	}

}
