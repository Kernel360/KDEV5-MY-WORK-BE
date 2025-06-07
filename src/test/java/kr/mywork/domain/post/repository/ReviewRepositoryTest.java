package kr.mywork.domain.post.repository;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import kr.mywork.base.annotations.RdbRepositoryTest;
import kr.mywork.domain.post.service.dto.ReviewSelectResponse;

@RdbRepositoryTest(basePackages = {
	"kr.mywork.infrastructure.post.rdb",
	"kr.mywork.common.rdb.config"})
class ReviewRepositoryTest {

	@Autowired
	private ReviewRepository reviewRepository;

	@Test
	@DisplayName("리뷰 목록 조회 성공")
	@Sql("classpath:sql/review-list-paging.sql")
	void 리뷰_목록_조회_성공() {
		// given
		final UUID postId = UUID.fromString("019748f3-9555-7056-8590-70dc155cc6fa");
		final int pageNumber = 1;
		final int pageSize = 10;

		// when
		final List<ReviewSelectResponse> reviewSelectResponses =
			reviewRepository.findParentReviewsWithPaging(postId, pageNumber, pageSize);

		// then
		assertSoftly(softlyAssertion -> {
			softlyAssertion.assertThat(reviewSelectResponses).hasSize(10);
		});
	}

	@Test
	@DisplayName("리뷰 자식 목록 조회 성공")
	@Sql("classpath:sql/review-list-paging.sql")
	void 대댓글_목록_조회_성공() {
		// given
		final UUID postId = UUID.fromString("019748f3-9555-7056-8590-70dc155cc6fa");
		final List<UUID> parentReviewIds = List.of(UUID.fromString("019748f2-244f-702d-aa8c-a7b27d255c2e"));

		// when
		final List<ReviewSelectResponse> reviewSelectResponses = reviewRepository.findByIds(postId, parentReviewIds);

		// then
		assertSoftly(softlyAssertion -> {
			softlyAssertion.assertThat(reviewSelectResponses).hasSize(2);
		});
	}

}
