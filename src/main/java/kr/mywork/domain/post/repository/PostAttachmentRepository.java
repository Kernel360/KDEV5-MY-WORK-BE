package kr.mywork.domain.post.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.post.model.PostAttachment;

public interface PostAttachmentRepository {
	PostAttachment save(PostAttachment postAttachment);
	Long countByDeletedAndActive(UUID postId, boolean deleted, boolean active);
	Optional<PostAttachment> findById(UUID postAttachmentId);
	boolean existsByFileNameAndDeleted(UUID postId, String fileName, boolean deleted);
	List<PostAttachment> findAllByPostIdDeletedAndActive(UUID postId, Boolean deleted, Boolean active);
}
