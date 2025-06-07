package kr.mywork.infrastructure.post.rdb;

import static kr.mywork.domain.post.model.QReview.review;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.post.model.QReview;
import kr.mywork.domain.post.model.Review;
import kr.mywork.domain.post.repository.JpaReviewRepository;
import kr.mywork.domain.post.repository.ReviewRepository;
import kr.mywork.domain.post.service.dto.ReviewSelectResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslReviewRepository implements ReviewRepository {

	private final JpaReviewRepository jpaReviewRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public Review save(final Review review) {
		return jpaReviewRepository.save(review);
	}

	@Override
	public Optional<Review> findById(final UUID reviewId) {
		final Review selectedReview = queryFactory.selectFrom(review)
			.where(QReview.review.id.eq(reviewId))
			.limit(1)
			.fetchOne();

		return Optional.ofNullable(selectedReview);
	}

	@Override
	public List<ReviewSelectResponse> findParentReviewsWithPaging(final UUID postId, final Integer pageNumber,
		final int pageSize) {

		final int offset = (pageNumber - 1) * pageSize;

		return queryFactory.select(Projections.constructor(
				ReviewSelectResponse.class,
				review.id,
				review.authorName,
				review.comment,
				review.companyName,
				review.parentId,
				Expressions.constant(Collections.emptyList()),
				review.createdAt))
			.from(review)
			.where(review.postId.eq(postId), review.deleted.eq(false), review.parentId.isNull())
			.limit(pageSize)
			.offset(offset)
			.orderBy(review.createdAt.desc())
			.fetch();
	}

	@Override
	public List<ReviewSelectResponse> findByIds(final UUID postId, final List<UUID> parentIds) {
		return queryFactory.select(Projections.constructor(
				ReviewSelectResponse.class,
				review.id,
				review.authorName,
				review.comment,
				review.companyName,
				review.parentId,
				Expressions.constant(Collections.emptyList()),
				review.createdAt))
			.from(review)
			.where(
				review.postId.eq(postId),
				review.deleted.eq(false),
				review.parentId.in(parentIds))
			.orderBy(review.createdAt.desc())
			.fetch();
	}
}
