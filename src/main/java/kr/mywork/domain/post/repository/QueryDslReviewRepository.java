package kr.mywork.domain.post.repository;

import org.springframework.stereotype.Repository;

import kr.mywork.domain.post.model.Review;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslReviewRepository implements ReviewRepository {

	private final JpaReviewRepository jpaReviewRepository;

	@Override
	public Review save(final Review review) {
		return jpaReviewRepository.save(review);
	}
}
