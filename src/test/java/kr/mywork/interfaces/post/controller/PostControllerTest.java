package kr.mywork.interfaces.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.domain.project.service.ProjectService;
import kr.mywork.domain.project_member.service.ProjectMemberService;
import kr.mywork.interfaces.project.controller.ProjectController;
import kr.mywork.interfaces.project.controller.dto.request.ProjectCreateWebRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProjectController.class,
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfigurer.class)}, //security 설정을 종료하기 위한 설정
	excludeAutoConfiguration = SecurityAutoConfiguration.class)
class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private ProjectService projectService;

	@MockitoBean
	private ProjectMemberService projectMemberService;

	@Test
	@DisplayName("프로젝트 생성 성공")
	void 프로젝트_생성_성공() throws Exception {
		// given
		final UUID createdProjectId = UUID.fromString("01973ee1-ce0a-7350-afbd-2467ad13253a");
		final UUID devCompanyId = UUID.fromString("01973ee2-3c1c-792f-9fa9-1e61fd218a6f");
		final UUID clientCompanyId = UUID.fromString("01973ee2-7e42-7c7a-846d-caf8979cd023");

		// when(projectService.createProject(any())).thenReturn(createdProjectId);

		final ProjectCreateWebRequest projectCreateWebRequest = new ProjectCreateWebRequest("프로젝트 이름",
			LocalDateTime.of(2025, 6, 5, 12, 0),
			LocalDateTime.of(2025, 7, 5, 12, 0),
			"COMPLETED", "프로젝트 설명입니다.", devCompanyId, clientCompanyId,100L);

		final String requestBody = objectMapper.writeValueAsString(projectCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/projects")
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(print());
	}

	@ParameterizedTest
	@MethodSource("projectCreateFailMethodSource")
	@DisplayName("프로젝트 생성 실패 (유효하지 않는 입력 값)")
	void 프로젝트_생성_실패_유효하지_않는_입력_값(final String name, final LocalDateTime startAt, final LocalDateTime endAt,
		final String step, final String detail, final UUID devCompanyId, final UUID clientCompanyId) throws Exception {
		// given
		final UUID createdProjectId = UUID.fromString("01973ee1-ce0a-7350-afbd-2467ad13253a");

		// when(projectService.createProject(any())).thenReturn(createdProjectId);

		final ProjectCreateWebRequest projectCreateWebRequest = new ProjectCreateWebRequest(
			name, startAt, endAt, step, detail, devCompanyId, clientCompanyId,100L);

		final String requestBody = objectMapper.writeValueAsString(projectCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/projects")
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));

		// then
		result.andExpectAll(
				status().isBadRequest(),
				jsonPath("$.result").value(ResultType.ERROR.name()),
				jsonPath("$.data").doesNotExist(),
				jsonPath("$.error").exists())
			.andDo(print());
	}

	public static Stream<Arguments> projectCreateFailMethodSource() {
		return Stream.of(
			arguments( // 프로젝트 이름 빈 값
				"", LocalDateTime.of(2025, 6, 5, 12, 0),
				LocalDateTime.of(2025, 7, 5, 12, 0),
				"계약 단계", "프로젝트 설명입니다.", UUID.fromString("01973ee2-3c1c-792f-9fa9-1e61fd218a6f"),
				UUID.fromString("01973ee2-7e42-7c7a-846d-caf8979cd023")),
			arguments( // 프로젝트 단계 빈 값
				"프로젝트 이름", LocalDateTime.of(2025, 6, 5, 12, 0),
				LocalDateTime.of(2025, 7, 5, 12, 0),
				"", "프로젝트 설명입니다.", UUID.fromString("01973ee2-3c1c-792f-9fa9-1e61fd218a6f"),
				UUID.fromString("01973ee2-7e42-7c7a-846d-caf8979cd023")),
			arguments( // 프로젝트 설명 빈 값
				"프로젝트 이름", LocalDateTime.of(2025, 6, 5, 12, 0),
				LocalDateTime.of(2025, 7, 5, 12, 0),
				"프로젝트 단계", "", UUID.fromString("01973ee2-3c1c-792f-9fa9-1e61fd218a6f"),
				UUID.fromString("01973ee2-7e42-7c7a-846d-caf8979cd023"))
		);
	}

}
