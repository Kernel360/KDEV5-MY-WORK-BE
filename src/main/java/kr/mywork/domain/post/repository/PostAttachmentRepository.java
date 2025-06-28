package kr.mywork.domain.post.repository;

import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.post.model.PostAttachment;

public interface PostAttachmentRepository {
	PostAttachment save(PostAttachment postAttachment);
	Long countByDeletedAndActive(UUID postId, boolean deleted, boolean active);
	Optional<PostAttachment> findById(UUID postAttachmentId);
}
