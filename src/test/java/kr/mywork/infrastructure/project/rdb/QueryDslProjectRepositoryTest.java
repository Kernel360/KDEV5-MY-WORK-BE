package kr.mywork.infrastructure.project.rdb;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;

import kr.mywork.base.annotations.RdbRepositoryTest;
import kr.mywork.domain.project.model.Project;

@RdbRepositoryTest(
	basePackages = {
		"kr.mywork.infrastructure.project.rdb",
		"kr.mywork.common.rdb.config"})
class QueryDslProjectRepositoryTest {

	@Autowired
	private QueryDslProjectRepository queryDslProjectRepository;

	@Value("${dashboard.page.size}")
	private int dashboardPageSize;

	@Test
	@DisplayName("프로젝트 5일 이내 마감 임박 목록 조회 성공")
	@Sql("classpath:sql/projects_near_deadline02.sql")
	void 프로젝트_5일_이내_마감_임박_목록_조회_성공() {
		// given
		final List<UUID> projectIds = List.of(
			UUID.fromString("0197ce0a-0573-7aed-ac15-10384b9a21ec"),
			UUID.fromString("0197ce0a-38b4-76d1-ba71-a3913e0373da"),
			UUID.fromString("0197ce0a-b5fd-776e-a304-b90e2441b143"),
			UUID.fromString("0197ce0a-d6e7-752e-97f6-765a92821508"),
			UUID.fromString("0197ce0a-eba0-7601-9edf-2143363d3a4f"),
			UUID.fromString("0197ce0a-fa9c-78e2-86c1-b280542ebcb0"));

		final LocalDateTime baseDate = LocalDateTime.of(2025, 7, 3, 0, 0);

		// when
		final List<Project> deadLineProjects = queryDslProjectRepository.findAllNearDeadlineProjectsByProjectIds(
			projectIds, 1, dashboardPageSize, baseDate);

		// then
		assertSoftly(softAssertion -> {
			softAssertion.assertThat(deadLineProjects)
				.extracting(Project::getEndAt)
				.allSatisfy(endAt -> {
					final LocalDateTime deadlineStartRange = baseDate.toLocalDate().atStartOfDay();
					final LocalDateTime deadLineEndRange = baseDate.plusDays(5).toLocalDate().atTime(23, 59, 59);

					softAssertion.assertThat(endAt)
						.isAfterOrEqualTo(deadlineStartRange)
						.isBeforeOrEqualTo(deadLineEndRange);
				});
		});
	}
}
