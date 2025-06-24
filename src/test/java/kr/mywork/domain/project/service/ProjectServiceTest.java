package kr.mywork.domain.project.service;

import kr.mywork.base.listener.DataCleanUpExecutionListener;
import kr.mywork.domain.project.service.dto.response.ProjectSelectResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestExecutionListeners(value = {
	DataCleanUpExecutionListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;

	@ParameterizedTest
	@MethodSource("projectKeywordSearchSource")
	@DisplayName("프로젝트 키워드와 타입을 기반으로 프로젝트 목록 조회")
	@Sql("classpath:sql/project-keyword-search.sql")
	void 프로젝트_키워드와_타입을_기반으로_프로젝트_목록_조회(
		final String keywordType, final String keyword, final String step, final int page, final int expected) {
		// when
		final List<ProjectSelectResponse> projectSelectResponses =
			projectService.findProjectsBySearchConditionWithPaging(keywordType, keyword, step, page);

		// then
		assertThat(projectSelectResponses).hasSize(expected);
	}

	private static Stream<Arguments> projectKeywordSearchSource() {
		return Stream.of(
			Arguments.arguments("DEV_COMPANY_NAME", "개발사", null, 1, 10),
			Arguments.arguments("CLIENT_COMPANY_NAME", "고객사", null, 1, 10),
			Arguments.arguments("PROJECT_NAME", "프로젝트", null, 1, 10),
			Arguments.arguments("PROJECT_NAME", "프로젝트", "CONTRACT", 1, 4),
			Arguments.arguments(null, null, "CONTRACT", 1, 4),
			Arguments.arguments(null, null, "IN_PROGRESS", 1, 4),
			Arguments.arguments(null, null, "PAYMENT", 1, 4),
			Arguments.arguments(null, null, "PAYMENT", 2, 0)
		);
	}

	@ParameterizedTest
	@MethodSource("projectKeywordTotalCountSource")
	@DisplayName("프로젝트 키워드와 타입을 기반으로 프로젝트 총 갯수 조회")
	@Sql("classpath:sql/project-keyword-search.sql")
	void 프로젝트_키워드와_타입을_기반으로_프로젝트_총_갯수_조회(
		final String keywordType, final String keyword, final String step, final int expected) {
		// when
		final Long totalCount = projectService.countTotalProjectsByCondition(keywordType, keyword, step);

		// then
		assertThat(totalCount).isEqualTo(expected);
	}

	private static Stream<Arguments> projectKeywordTotalCountSource() {
		return Stream.of(
			Arguments.arguments("DEV_COMPANY_NAME", "개발사", null, 16),
			Arguments.arguments("CLIENT_COMPANY_NAME", "고객사", null, 16),
			Arguments.arguments("PROJECT_NAME", "프로젝트", null, 16),
			Arguments.arguments("PROJECT_NAME", "프로젝트", "CONTRACT", 4),
			Arguments.arguments(null, null, "CONTRACT", 4),
			Arguments.arguments(null, null, "IN_PROGRESS", 4),
			Arguments.arguments(null, null, "PAYMENT", 4)
		);
	}
}
