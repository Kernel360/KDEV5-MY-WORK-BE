package kr.mywork.domain.post.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.post.model.PostId;

public interface PostIdRepository {
	UUID save(UUID postId);

	Optional<PostId> findById(UUID id);

	Long deleteIssuedPostIdsLessOrEqualLimitTime(LocalDateTime limitTime);
}
