package kr.mywork.domain.post.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import kr.mywork.domain.post.service.dto.response.PostSelectResponse;

public interface PostRepository {
	Post save(PostCreateRequest postCreateRequest);

	Optional<Post> findById(UUID id);

	List<PostSelectResponse> findPostsBySearchConditionWithPaging(int page, int postPageSize, UUID projectStepId,
		String keyword, Boolean deleted, UUID projectId, String keywordType, String approval);

	Long countTotalPostsByCondition(UUID projectStepId, String keyword, Boolean deleted, UUID projectId, String keywordType, String approval);
}
