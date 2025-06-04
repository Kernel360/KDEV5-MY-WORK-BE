package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepCreateWebRequest;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepUpdateWebRequest;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepsCreateWebRequest;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepsUpdateWebRequest;

public class ProjectStepDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("프로젝트 단계 생성 성공")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 프로젝트_단계_생성_성공() throws Exception {
		// given
		// TODO Project 생성 API 개발 후, ProjectId 생성 및 검증 샘플 데이터 추가 필요
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
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-id-create-success", projectIdCreateSuccessResource()));
	}

	private ResourceSnippet projectIdCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project Step API")
				.summary("프로젝트 단계 생성 API")
				.description("프로젝트 단계를 생성한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.createdStepCount").type(JsonFieldType.NUMBER).description("생성한 프로젝트 단계 갯수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("프로젝트 단계 수정 성공")
	@Sql("classpath:sql/project-step-update.sql")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 프로젝트_단계_수정_성공() throws Exception {
		// given
		// TODO Project 생성 API 개발 후, ProjectId 생성 및 검증 샘플 데이터 추가 필요

		final List<ProjectStepUpdateWebRequest> projectStepCreateWebRequests = List.of(
			new ProjectStepUpdateWebRequest(UUID.fromString("01972ea5-0bc1-7b72-87d8-bdc93b2049c4"), "기획_수정", 1),
			new ProjectStepUpdateWebRequest(UUID.fromString("01972ea5-3e04-7ced-b90e-9c5c4b83b733"), "개발_수정", 2),
			new ProjectStepUpdateWebRequest(UUID.fromString("01972ea5-5554-757d-9c11-a1fe2587bfde"), "QA_수정", 3),
			new ProjectStepUpdateWebRequest(UUID.fromString("01972ea5-73ff-75e1-9083-d1d51a0f186a"), "운영_수정", 4));

		final ProjectStepsUpdateWebRequest projectStepsUpdateWebRequest =
			new ProjectStepsUpdateWebRequest(projectStepCreateWebRequests);

		final String requestBody = objectMapper.writeValueAsString(projectStepsUpdateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(put("/api/projects/{projectId}/steps", "0197207e-7331-7000-946b-a29a79a82424")
			.content(requestBody)
			.contentType(MediaType.APPLICATION_JSON));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-id-update-success", projectIdUpdateSuccessResource()));
	}

	private ResourceSnippet projectIdUpdateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project Step API")
				.summary("프로젝트 단계 일괄 수정 API")
				.description("프로젝트 단계를 일괄 수정한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.projectSteps.[].projectStepId").type(JsonFieldType.STRING).description("프로젝트 단계 아이디"),
					fieldWithPath("data.projectSteps.[].title").type(JsonFieldType.STRING).description("프로젝트 단계 이름"),
					fieldWithPath("data.projectSteps.[].orderNumber").type(JsonFieldType.NUMBER).description("프로젝트 단계 순서"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
}
