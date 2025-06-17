package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import kr.mywork.interfaces.dashboard.controller.dto.request.DashboardCountSummaryWebRequest;

public class DashboardDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("대쉬보드 summery total 갯수 조회")
	@Sql("classpath:sql/dashboard-summery-count.sql")
	void 대쉬보드_써머리_총갯수_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();
		final DashboardCountSummaryWebRequest dashboardCountSummaryWebRequest =
			new DashboardCountSummaryWebRequest("SYSTEMADMIN",null,null);
		final String requestBody = objectMapper.writeValueAsString(dashboardCountSummaryWebRequest);

		// when
		ResultActions result = mockMvc.perform(
			get("/api/dashboard/totalSummery")
				.content(requestBody)
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

}
