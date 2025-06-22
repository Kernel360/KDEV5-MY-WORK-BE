package kr.mywork.docs;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.dashboard.controller.dto.request.DashboardCountSummaryWebRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DashboardDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("대쉬보드 summery total 갯수 조회")
	@Sql("classpath:sql/dashboard-summery-count.sql")
	void 대쉬보드_써머리_총갯수_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		// when
		ResultActions result = mockMvc.perform(
			get("/api/dashboard/total-summery")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
			status().isOk(),
			jsonPath("$.result").value(ResultType.SUCCESS.name()),
			jsonPath("$.data").exists(),
			jsonPath("$.error").doesNotExist()
		).andDo(document("dashboard-get-count-summery-success", TotalCountSummerySuccessResource()));
	}

	private ResourceSnippet TotalCountSummerySuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Dashboard API")
				.summary("대시보드 서머리 조회 API")
				.description("(모든,진행중,완료) 프로젝트 갯수를 보여준다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("모든 프로젝트 갯수"),
					fieldWithPath("data.inProgressCount").type(JsonFieldType.NUMBER).description("진행중인 프로젝트 갯수"),
					fieldWithPath("data.completedCount").type(JsonFieldType.NUMBER).description("완료된 프로젝트 갯수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}
	@Test
	@DisplayName("가장 이슈있는 프로젝트")
	@Sql("classpath:sql/dashboard-mostpost-top-five.sql")
	void 대시보드_가장_게시글많은_프로젝트_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();
		// when
		ResultActions result = mockMvc.perform(
			get("/api/dashboard/popular-projects")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
			status().isOk(),
			jsonPath("$.result").value(ResultType.SUCCESS.name()),
			jsonPath("$.data").exists(),
			jsonPath("$.error").doesNotExist()
		).andDo(document("dashboard-most-post-top-five-success", topFviePostSuccessResource()));
	}

	private ResourceSnippet topFviePostSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Dashboard API")
				.summary("가장 이슈있는 프로젝트 API")
				.description("가장 게시글이 많은 프로젝트를 보여준다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.projects[].projectId").type(JsonFieldType.STRING).description("프로젝트 ID"),
					fieldWithPath("data.projects[].projectName").type(JsonFieldType.STRING).description("프로젝트명"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

}
