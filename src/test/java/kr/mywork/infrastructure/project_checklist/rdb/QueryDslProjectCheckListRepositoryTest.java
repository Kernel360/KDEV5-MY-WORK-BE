package kr.mywork.infrastructure.project_checklist.rdb;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import kr.mywork.base.annotations.RdbRepositoryTest;
import kr.mywork.domain.project_checklist.repository.ProjectCheckListRepository;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectStepCheckListCountResponse;

@RdbRepositoryTest(basePackages = {
	"kr.mywork.infrastructure.project_checklist.rdb",
	"kr.mywork.infrastructure.project_step.rdb",
	"kr.mywork.common.rdb.config"})
class QueryDslProjectCheckListRepositoryTest {

	@Autowired
	private ProjectCheckListRepository projectCheckListRepository;

	@Test
	@DisplayName("프로젝트 스텝별 체크리스트 전체 갯수 조회")
	@Sql("classpath:sql/project_checklist_approval.sql")
	void 프로젝트_스텝별_체크리스트_전체_갯수_조회() {
		// given
		final List<UUID> projectStepIds = List.of(
			UUID.fromString("01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01"),
			UUID.fromString("01991f59-2acb-7a72-a64f-5e1a257bbbe2"),
			UUID.fromString("01991f59-6ecf-7a2a-8bb4-92707f10cc0c"));

		// when
		final List<ProjectStepCheckListCountResponse> projectStepCheckListTotalCountResponses =
			projectCheckListRepository.findProgressCountGroupByProjectStepIdAndApproval(projectStepIds, null);

		// then
		assertSoftly(softly -> {
			softly.assertThat(projectStepCheckListTotalCountResponses).extracting("count")
				.containsExactly(10L, 10L, 10L);
		});
	}

	@Test
	@DisplayName("프로젝트 스텝별 체크리스트 승인 갯수 조회")
	@Sql("classpath:sql/project_checklist_approval.sql")
	void 프로젝트_스텝별_체크리스트_승인_갯수_조회() {
		// given
		final List<UUID> projectStepIds = List.of(
			UUID.fromString("01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01"),
			UUID.fromString("01991f59-2acb-7a72-a64f-5e1a257bbbe2"),
			UUID.fromString("01991f59-6ecf-7a2a-8bb4-92707f10cc0c"));

		// when
		final List<ProjectStepCheckListCountResponse> projectStepCheckListTotalCountResponses =
			projectCheckListRepository.findProgressCountGroupByProjectStepIdAndApproval(projectStepIds, "APPROVED");

		// then
		assertSoftly(softly -> {
			softly.assertThat(projectStepCheckListTotalCountResponses).extracting("count")
				.containsExactly(7L, 7L, 7L);
		});
	}
}
