package kr.mywork.infrastructure.post.rdb;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import kr.mywork.base.annotations.RdbRepositoryTest;
import kr.mywork.domain.post.repository.PostIdRepository;

@RdbRepositoryTest(basePackages = {
	"kr.mywork.infrastructure.post.rdb",
	"kr.mywork.common.rdb.config"})
class QueryDslPostIdRepositoryTest {

	@Autowired
	private PostIdRepository postIdRepository;

	@Test
	@DisplayName("제한 시간을 지난 날짜의 게시글 아이디 삭제 성공")
	@Sql("classpath:sql/issued-post-id-bulk-deletion.sql")
	void 제한_시간을_지난_날짜의_게시글_아이디_삭제_성공 () {
	    // given
		final LocalDateTime limitTime = LocalDateTime.of(2025, 6, 30, 16, 0);

	    // when
		final Long deletionCount = postIdRepository.deleteIssuedPostIdsLessOrEqualLimitTime(limitTime);

		// then
		assertThat(deletionCount).isEqualTo(4);
	}

}
