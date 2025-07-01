package kr.mywork.infrastructure.post.rdb;

import static com.querydsl.core.types.ExpressionUtils.count;
import static kr.mywork.domain.post.model.QPostAttachment.postAttachment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.post.model.PostAttachment;
import kr.mywork.domain.post.repository.PostAttachmentRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslPostAttachmentRepository implements PostAttachmentRepository {

	private final JPAQueryFactory queryFactory;
	private final JpaPostAttachmentRepository jpaPostAttachmentRepository;

	@Override
	public PostAttachment save(final PostAttachment postAttachment) {
		return jpaPostAttachmentRepository.save(postAttachment);
	}

	@Override
	public Long countByDeletedAndActive(final UUID postId, final boolean deleted, final boolean active) {
		return queryFactory.select(count(postAttachment.id))
			.from(postAttachment)
			.where(postAttachment.postId.eq(postId), postAttachment.deleted.eq(deleted), postAttachment.active.eq(active))
			.fetchOne();
	}

	@Override
	public Optional<PostAttachment> findById(final UUID postAttachmentId) {
		final PostAttachment findPostAttachment = queryFactory.selectFrom(postAttachment)
			.where(postAttachment.id.eq(postAttachmentId))
			.limit(1)
			.fetchFirst();

		return Optional.ofNullable(findPostAttachment);
	}

	@Override
	public boolean existsByFileNameAndDeleted(final UUID postId, final String fileName, final boolean deleted) {
		return queryFactory.select(postAttachment.id)
			.from(postAttachment)
			.where(
				postAttachment.postId.eq(postId),
				postAttachment.fileName.eq(fileName),
				postAttachment.deleted.eq(deleted))
			.fetchFirst() != null;
	}

	@Override
	public List<PostAttachment> findAllByPostIdDeletedAndActive(final UUID postId, final Boolean deleted,
		final Boolean active) {
		return queryFactory.selectFrom(postAttachment)
			.where(
				postAttachment.postId.eq(postId),
				eqDeleted(deleted),
				eqActive(active))
			.fetch();
	}

	@Override
	public List<PostAttachment> findAllInPostAttachmentIdsByDeletedAndActive(final List<UUID> postAttachmentIds,
		final Boolean deleted, final Boolean active) {

		return queryFactory.selectFrom(postAttachment)
			.where(
				postAttachment.id.in(postAttachmentIds),
				eqDeleted(deleted),
				eqActive(active))
			.fetch();
	}

	private BooleanExpression eqDeleted(final Boolean deleted) {
		if (deleted == null) {
			return null;
		}

		return postAttachment.deleted.eq(deleted);
	}

	private BooleanExpression eqActive(final Boolean active) {
		if (active == null) {
			return null;
		}

		return postAttachment.active.eq(active);
	}
}
