package kr.mywork.interfaces.project_step.controller;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.domain.project_step.serivce.ProjectStepService;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepCreateWebRequest;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepsCreateWebRequest;

@WebMvcTest(ProjectStepController.class)
class ProjectStepControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private ProjectStepService projectStepService;

	@Test
	@DisplayName("프로젝트 단계 목록 생성 성공")
	void 프로젝트_단계_목록_생성_성공() throws Exception {
		// given
		when(projectStepService.saveAll(any(), any())).thenReturn(7);

		final UUID projectId = UUID.fromString("0197207e-7331-7000-946b-a29a79a82424");

		final List<ProjectStepCreateWebRequest> projectStepCreateWebRequests = List.of(
			new ProjectStepCreateWebRequest("기획", 1),
			new ProjectStepCreateWebRequest("개발", 2),
			new ProjectStepCreateWebRequest("QA", 3),
			new ProjectStepCreateWebRequest("운영", 4));

		final ProjectStepsCreateWebRequest projectStepsCreateWebRequest = new ProjectStepsCreateWebRequest(
			projectId, projectStepCreateWebRequests);

		final String requestBody = objectMapper.writeValueAsString(projectStepsCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/projects/steps")
			.content(requestBody)
			.contentType(MediaType.APPLICATION_JSON));

		// then
		result.andExpectAll(
			status().isOk(),
			jsonPath("$.result").value(ResultType.SUCCESS.name()),
			jsonPath("$.data").exists(),
			jsonPath("$.error").doesNotExist());
	}

	@ParameterizedTest
	@MethodSource("projectStepFailMethodSource")
	@DisplayName("프로젝트 단계 목록 유효하지 않은 입력값 실패")
	void 프로젝트_단계_목록_유효하지_않은_입력값_실패(
		final UUID projectId, final List<ProjectStepCreateWebRequest> projectStepCreateWebRequests) throws Exception {
		// given
		when(projectStepService.saveAll(any(), any())).thenReturn(7);

		final ProjectStepsCreateWebRequest projectStepsCreateWebRequest = new ProjectStepsCreateWebRequest(
			projectId, projectStepCreateWebRequests);

		final String requestBody = objectMapper.writeValueAsString(projectStepsCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/projects/steps")
			.content(requestBody)
			.contentType(MediaType.APPLICATION_JSON));

		// then
		result.andExpectAll(
				status().is4xxClientError(),
				jsonPath("$.result").value(ResultType.ERROR.name()),
				jsonPath("$.data").doesNotExist(),
				jsonPath("$.error").exists())
			.andDo(print());
	}

	private static Stream<Arguments> projectStepFailMethodSource() {
		return Stream.of(
			arguments(
				UUID.fromString("0197207e-7331-7000-946b-a29a79a82424"),
				List.of(new ProjectStepCreateWebRequest("기획", -1))), // 순서 음수 값
			arguments(
				UUID.fromString("0197207e-7331-7000-946b-a29a79a82424"),
				List.of(new ProjectStepCreateWebRequest("0123456789012345678901234567891", 1))), // 제목 길이 초과
			arguments(
				UUID.fromString("0197207e-7331-7000-946b-a29a79a82424"),
				List.of(
					new ProjectStepCreateWebRequest("기획", -1), // 순서 음수 값
					new ProjectStepCreateWebRequest("0123456789012345678901234567891", 1) // 제목 길이 초과
				))
		);
	}
}
