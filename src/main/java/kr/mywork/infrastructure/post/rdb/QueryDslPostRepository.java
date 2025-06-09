package kr.mywork.infrastructure.post.rdb;

import static kr.mywork.domain.company.model.QCompany.*;
import static kr.mywork.domain.company.model.QCompany.company;
import static kr.mywork.domain.post.model.QPost.*;
import static kr.mywork.domain.project_step.model.QProjectStep.*;

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
		UUID projectStepId, String keyword, Boolean deleted, UUID projectId, String keywordType, String approval) {

		final int offset = (page - 1) * postPageSize;

		return jpaQueryFactory.select(Projections.constructor(PostSelectResponse.class,
				post.id,
				post.createdAt,
				post.authorName,
				post.title,
				post.approval,
				projectStep.title
			))
			.from(post)
			.join(projectStep)
			.on(post.projectStepId.eq(projectStep.id))
			.where(
				eqProjectId(projectId),
				eqProjectStepId(projectStepId),
				eqDeleted(deleted),
				containsKeyword(keywordType, keyword),
				eqApproval(approval))
			.offset(offset)
			.limit(postPageSize)
			.fetch();
	}

	private BooleanExpression containsKeyword(final String searchType, final String keyword) {
		if (searchType == null) {
			return null;
		}

		if (keyword == null || keyword.isEmpty()) {
			return null;
		}

		return switch (searchType) {
			case "AUTHORNAME" -> post.authorName.containsIgnoreCase(keyword);
			case "TITLE" -> post.title.containsIgnoreCase(keyword);
			default -> null;
		};
	}

	@Override
	public Long countTotalPostsByCondition(UUID projectStepId, String keyword, Boolean deleted, UUID projectId,
		String keywordType, String approval) {
		return jpaQueryFactory.select(post.id.count())
			.from(post)
			.join(projectStep)
			.on(post.projectStepId.eq(projectStep.id))
			.where(
				eqProjectId(projectId),
				eqProjectStepId(projectStepId),
				eqDeleted(deleted),
				containsKeyword(keywordType, keyword),
				eqApproval(approval)
			)
			.fetchOne();
	}

	private BooleanExpression eqProjectStepId(UUID projectStepId) {
		if (projectStepId == null) {
			return null;
		}

		return post.projectStepId.eq(projectStepId);
	}

	private BooleanExpression eqProjectId(UUID projectId) {
		if (projectId == null) {
			return null;
		}

		return post.projectId.eq(projectId);
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

	private BooleanExpression eqApproval(String approval) {
		if (approval == null) {
			return null;
		}

		return post.approval.eq(approval);
	}
}
