package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
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
	void 프로젝트_단계_생성_성공() throws Exception {
		// given
		// TODO Project 생성 API 개발 후, ProjectId 생성 및 검증 샘플 데이터 추가 필요
		final String accessToken = createDevAdminAccessToken();

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
			.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
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
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.createdStepCount").type(JsonFieldType.NUMBER).description("생성한 프로젝트 단계 갯수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("프로젝트 단계 수정 성공")
	@Sql("classpath:sql/project-step-update.sql")
	void 프로젝트_단계_수정_성공() throws Exception {
		// given
		// TODO Project 생성 API 개발 후, ProjectId 생성 및 검증 샘플 데이터 추가 필요
		final String accessToken = createDevAdminAccessToken();

		final List<ProjectStepUpdateWebRequest> projectStepCreateWebRequests = List.of(
			new ProjectStepUpdateWebRequest(UUID.fromString("01972ea5-0bc1-7b72-87d8-bdc93b2049c4"), "기획_수정", 1),
			new ProjectStepUpdateWebRequest(UUID.fromString("01972ea5-3e04-7ced-b90e-9c5c4b83b733"), "개발_수정", 2),
			new ProjectStepUpdateWebRequest(UUID.fromString("01972ea5-5554-757d-9c11-a1fe2587bfde"), "QA_수정", 3),
			new ProjectStepUpdateWebRequest(UUID.fromString("01972ea5-73ff-75e1-9083-d1d51a0f186a"), "운영_수정", 4));

		final ProjectStepsUpdateWebRequest projectStepsUpdateWebRequest =
			new ProjectStepsUpdateWebRequest(projectStepCreateWebRequests);

		final String requestBody = objectMapper.writeValueAsString(projectStepsUpdateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			put("/api/projects/{projectId}/steps", "0197207e-7331-7000-946b-a29a79a82424")
				.content(requestBody)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
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
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.projectSteps.[].projectStepId").type(JsonFieldType.STRING)
						.description("프로젝트 단계 아이디"),
					fieldWithPath("data.projectSteps.[].title").type(JsonFieldType.STRING).description("프로젝트 단계 이름"),
					fieldWithPath("data.projectSteps.[].orderNumber").type(JsonFieldType.NUMBER)
						.description("프로젝트 단계 순서"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("프로젝트 단계 조회 성공")
	@Sql("classpath:sql/project-step-get.sql")
	void 프로젝트_단계_조회_성공() throws Exception {
		//given
		final String accessToken = createUserAccessToken();

		UUID projectId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e");

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/projects/{projectId}/steps", projectId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-get-steps-success", projectGetSetpsSuccessResource()));
	}

	public ResourceSnippet projectGetSetpsSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project Step API")
				.summary("프로젝트 단계 조회 API")
				.description("프로젝트 단계를 조회한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.steps[].projectStepId").type(JsonFieldType.STRING)
						.description("프로젝트 단계 아이디"),
					fieldWithPath("data.steps[].title").type(JsonFieldType.STRING).description("프로젝트 단계 이름"),
					fieldWithPath("data.steps[].orderNum").type(JsonFieldType.NUMBER)
						.description("프로젝트 단계 순서"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("프로젝트 단계 와 단게별 게시글 수 조회 성공")
	@Sql("classpath:sql/project-step-get-with-count.sql")
	void 프로젝트_단계_및_개시글수_조회_성공() throws Exception {
		//given
		final String accessToken = createUserAccessToken();

		UUID projectId = UUID.fromString("0197893a-ad34-734c-97d7-2e0dd6429247");

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/projects/{projectId}/steps-with-count", projectId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-get-steps-with-count-success", projectGetSetpsWithCountSuccessResource()));
	}

	public ResourceSnippet projectGetSetpsWithCountSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project Step API")
				.summary("프로젝트 단계 및 개시글수 조회 API")
				.description("프로젝트 단계를 조회한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.steps[].projectStepId").type(JsonFieldType.STRING)
						.description("프로젝트 단계 아이디"),
					fieldWithPath("data.steps[].title").type(JsonFieldType.STRING).description("프로젝트 단계 이름"),
					fieldWithPath("data.steps[].orderNum").type(JsonFieldType.NUMBER)
						.description("프로젝트 단계 순서"),
					fieldWithPath("data.steps[].totalCount").type(JsonFieldType.NUMBER)
						.description("프로젝트 단계의 개시글 수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
}
