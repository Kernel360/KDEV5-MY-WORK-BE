package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
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
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepCreateWebRequest;
import kr.mywork.interfaces.project_step.dto.request.ProjectStepsCreateWebRequest;

public class ProjectStepDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("프로젝트 단계 생성 성공")
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
				.tag("ProjectId API")
				.summary("프로젝트 단계 API")
				.description("프로젝트 단계를 생성한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.createdStepCount").type(JsonFieldType.NUMBER).description("생성한 프로젝트 단계 갯수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
}
