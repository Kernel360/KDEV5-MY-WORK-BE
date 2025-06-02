package kr.mywork.domain.post.repository;

import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;

public interface PostRepository {
	Post save(PostCreateRequest postCreateRequest);

	Optional<Post> findById(UUID id);
}
