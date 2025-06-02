package kr.mywork.infrastructure.post.rdb;

import org.springframework.stereotype.Repository;

import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslPostRepository implements PostRepository {

	private final JpaPostRepository postRepository;

	@Override
	public Post save(final PostCreateRequest postCreateRequest) {
		return postRepository.save(postCreateRequest.toEntity());
	}

}
