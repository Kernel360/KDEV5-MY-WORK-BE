package kr.mywork.domain.post.repository;

import static kr.mywork.domain.post.model.QReview.review;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.post.model.QReview;
import kr.mywork.domain.post.model.Review;
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
}
