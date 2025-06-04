package kr.mywork.infrastructure.post.rdb;

import static kr.mywork.domain.post.model.QPost.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import kr.mywork.domain.post.service.dto.response.PostSelectResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslPostRepository implements PostRepository {

	private final JpaPostRepository postRepository;
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Post save(final PostCreateRequest postCreateRequest) {
		return postRepository.save(postCreateRequest.toEntity());
	}

	@Override
	public Optional<Post> findById(UUID postId) {
		return postRepository.findById(postId);
	}

	public List<PostSelectResponse> findPostsBySearchConditionWithPaging(int page, int postPageSize,
		UUID projectStepId, String keyword, Boolean deleted) {

		final int offset = (page - 1) * postPageSize;

		return jpaQueryFactory.select(Projections.constructor(PostSelectResponse.class,
				post.id,
				post.createdAt,
				post.authorName,
				post.title,
				post.approval
			))
			.from(post)
			.where(
					eqProjectStepId(projectStepId),
					eqDeleted(deleted),
					eqKeyword(keyword))
			.offset(offset)
			.limit(postPageSize)
			.fetch();
	}

	@Override
	public Long countTotalPostsByCondition(UUID projectStepId, String keyword, Boolean deleted) {
		return jpaQueryFactory.select(post.id.count())
			.from(post)
			.where(
				eqProjectStepId(projectStepId),
				eqDeleted(deleted),
				eqKeyword(keyword)
			)
			.fetchOne();
	}

	private BooleanExpression eqProjectStepId(UUID projectStepId) {
		if (projectStepId == null) {
			return null;
		}

		return post.projectStepId.eq(projectStepId);
	}

	private BooleanExpression eqKeyword(String keyword) {
		if (keyword == null) {
			return null;
		}

		return post.title.like(keyword + "%");
	}

	private BooleanExpression eqDeleted(Boolean deleted) {
		if (deleted == null) {
			return null;
		}

		return post.deleted.eq(deleted);
	}
}
