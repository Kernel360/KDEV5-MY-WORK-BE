package kr.mywork.infrastructure.post.rdb;

import static kr.mywork.domain.post.model.QPostAttachment.postAttachment;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

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
	public Optional<PostAttachment> findById(final UUID postAttachmentId) {
		final PostAttachment findPostAttachment = queryFactory.selectFrom(postAttachment)
			.where(postAttachment.id.eq(postAttachmentId))
			.limit(1)
			.fetchFirst();

		return Optional.ofNullable(findPostAttachment);
	}
}
