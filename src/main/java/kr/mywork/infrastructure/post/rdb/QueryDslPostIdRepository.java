package kr.mywork.infrastructure.post.rdb;

import static kr.mywork.domain.post.model.QPostId.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.post.model.PostId;
import kr.mywork.domain.post.repository.PostIdRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslPostIdRepository implements PostIdRepository {

	private final JpaPostIdRepository jpaPostIdRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public UUID save(final UUID postId) {
		return jpaPostIdRepository.save(new PostId(postId)).getId();
	}

	@Override
	public Optional<PostId> findById(final UUID id) {
		final PostId findPostId = queryFactory.select(postId)
			.from(postId)
			.where(postId.id.eq(id))
			.fetchFirst();

		return Optional.ofNullable(findPostId);
	}

	@Override
	public Long deleteIssuedPostIdsLessOrEqualLimitTime(final LocalDateTime limitTime) {
		return queryFactory.delete(postId)
			.where(postId.createdAt.loe(limitTime))
			.execute();
	}
}
