package kr.mywork.domain.post.repository;

import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.post.model.PostAttachment;

public interface PostAttachmentRepository {
	PostAttachment save(PostAttachment postAttachment);
	Optional<PostAttachment> findById(UUID postAttachmentId);
}
