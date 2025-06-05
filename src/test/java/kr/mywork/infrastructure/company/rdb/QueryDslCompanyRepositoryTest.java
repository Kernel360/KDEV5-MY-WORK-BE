package kr.mywork.infrastructure.company.rdb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import kr.mywork.base.annotations.RdbRepositoryTest;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.response.CompanySelectResponse;

@RdbRepositoryTest(basePackages = {
	"kr.mywork.infrastructure.company.rdb",
	"kr.mywork.common.rdb.config"})
class QueryDslCompanyRepositoryTest {

	@Autowired
	private CompanyRepository companyRepository;

	@Test
	@DisplayName("기본 회사 목록 조회 성공")
	@Sql("classpath:sql/company-list.sql")
	void 기본_회사_목록_조회_성공() {
		// given
		int companyPageSize = 10;
		String companyType = "DEV";
		String keyword = null;
		Boolean deleted = null;

		// when
		final List<CompanySelectResponse> companies =
			companyRepository.findCompaniesBySearchConditionWithPaging(1, companyPageSize, companyType, keyword,
				deleted);

		// then
		assertThat(companies).hasSize(10);
	}

	@Test
	@DisplayName("회사 활성화 목록 조회 성공")
	@Sql("classpath:sql/company-list.sql")
	void 회사_활성화_목록_조회_성공() {
		// given
		String companyType = "DEV";
		String keyword = null;
		Boolean deleted = true;
		int companyPageSize = 10;

		// when
		final List<CompanySelectResponse> companies =
			companyRepository.findCompaniesBySearchConditionWithPaging(1, companyPageSize, companyType, keyword,
				deleted);

		// then
		assertSoftly(softly -> {
			softly.assertThat(companies).hasSize(10);
			softly.assertThat(companies).extracting("deleted").allMatch(deleted::equals);
		});
	}

	@Test
	@DisplayName("회사 검색어 목록 조회 성공 - 키워드 앞부분 일치")
	@Sql("classpath:sql/company-list.sql")
	void 회사_검색어_목록_조회_성공() {
		// given
		String companyType = "DEV";
		String keyword = "Company1";
		Boolean deleted = null;
		int companyPageSize = 10;

		// when
		final List<CompanySelectResponse> companies =
			companyRepository.findCompaniesBySearchConditionWithPaging(1, companyPageSize, companyType, keyword,
				deleted);

		// then
		assertThat(companies).hasSize(6);
	}

	@DisplayName("회사 검색어 목록 갯수 조회 성공")
	@ParameterizedTest
	@MethodSource("companyListMethodSource")
	@Sql("classpath:sql/company-list.sql")
	void 회사_검색어_목록_갯수_조회_성공(final String companyType, final String keyword, final Boolean deleted, final Long expected) {
		// given, when
		final Long totalCount = companyRepository.countTotalCompaniesByCondition(companyType, keyword, deleted);

		// then
		assertThat(totalCount).isEqualTo(expected);
	}

	private static Stream<Arguments> companyListMethodSource() {
		return Stream.of(
			Arguments.of("DEV", "Company1", null, 6L),
			Arguments.of("DEV", null, true, 15L),
			Arguments.of("CLIENT", null, true, 0L),
			Arguments.of("DEV", null, null, 15L)
		);
	}
}
