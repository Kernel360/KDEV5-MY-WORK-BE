package kr.mywork.docs;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import kr.mywork.common.api.support.response.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class DashboardDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("대쉬보드 summery total 갯수 조회")
	@Sql("classpath:sql/dashboard-summary-count.sql")
	void 대쉬보드_써머리_총갯수_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		// when
		ResultActions result = mockMvc.perform(
				get("/api/dashboard/total-summary")
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

	@Test
	@DisplayName("마감 임박 프로젝트 목록 조회 성공")
	@Sql("classpath:sql/project-near-deadline.sql")
	void 마감_임박_프로젝트_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/dashboard/projects/near-deadline")
				.param("page", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
			status().isOk(),
			jsonPath("$.result").value(ResultType.SUCCESS.name()),
			jsonPath("$.data").exists(),
			jsonPath("$.data.projects").isArray(),
			jsonPath("$.data.totalCount").isNumber(),
			jsonPath("$.error").doesNotExist()
		).andDo(document("project-near-deadline-success", projectNearDeadlineSuccessResource()));
	}

	private ResourceSnippet projectNearDeadlineSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Dashboard API")
				.summary("마감 임박 프로젝트 목록 조회 API")
				.description("현재 로그인한 사용자가 접근 가능한 마감 임박 프로젝트 목록과 총 개수를 조회한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.queryParameters(
					parameterWithName("page").description("페이지 번호 (1부터 시작)"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.projects").type(JsonFieldType.ARRAY).description("마감 임박 프로젝트 목록"),
					fieldWithPath("data.projects[].id").type(JsonFieldType.STRING).description("프로젝트 ID"),
					fieldWithPath("data.projects[].name").type(JsonFieldType.STRING).description("프로젝트 이름"),
					fieldWithPath("data.projects[].endAt").type(JsonFieldType.STRING).description("프로젝트 종료일"),
					fieldWithPath("data.projects[].dday").type(JsonFieldType.NUMBER).description("마감까지 남은 일수 (D-Day)"),
					fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("전체 마감 임박 프로젝트 수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보")
				)
				.build());
	}

	@Test
	@DisplayName("대시보드 결제 통계 조회 devAdmin 성공")
	@Sql("classpath:sql/dashboard-amount-dev-admin.sql")
	void 대시보드_결제_통계_개발사_주단위_조회_성공() throws Exception {
		// given
		final String accessToken = createDevAdminAccessToken();

		// when
		final ResultActions result = mockMvc.perform(
				get("/api/dashboard/project-amount")
						.param("chartType", "CHART_TYPE_WEEK")
						.contentType(MediaType.APPLICATION_JSON)
						.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist()
		).andDo(document("dashboard-amount-dev-admin-success", dashboardAmountDevAdminSuccessResource()));
	}

	private ResourceSnippet dashboardAmountDevAdminSuccessResource() {
		return resource(
				ResourceSnippetParameters.builder()
						.tag("Dashboard API")
						.summary("대시보드 결제 통계 조회 API")
						.description("대시보드 결제 통계를 조회한다.")
						.requestHeaders(
								headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
								headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
						.queryParameters(
								parameterWithName("chartType").description("결제 통계 차트 노출 기준(주단위,월단위)"))
						.responseFields(
								fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
								fieldWithPath("data.chartData[].label").type(JsonFieldType.STRING).description("결제 통계 차트에 노출명"),
								fieldWithPath("data.chartData[].totalAmount").type(JsonFieldType.NUMBER).description("결제 통계 총 금액"),
								fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보")
						)
						.build());
	}

	@Test
	@DisplayName("대시보드 결제 통계 조회 devAdmin 성공")
	@Sql("classpath:sql/dashboard-amount-dev-admin.sql")
	void 대시보드_결제_통계_개발사_월단위_조회_성공() throws Exception {
		// given
		final String accessToken = createDevAdminAccessToken();

		// when
		final ResultActions result = mockMvc.perform(
				get("/api/dashboard/project-amount")
						.param("chartType", "CHART_TYPE_MONTH")
						.contentType(MediaType.APPLICATION_JSON)
						.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist()
		).andDo(document("dashboard-amount-dev-admin-month-success", dashboardAmountDevAdminMonthSuccessResource()));
	}

	private ResourceSnippet dashboardAmountDevAdminMonthSuccessResource() {
		return resource(
				ResourceSnippetParameters.builder()
						.tag("Dashboard API")
						.summary("대시보드 결제 통계 조회 API")
						.description("대시보드 결제 월단위 통계를 조회한다.")
						.requestHeaders(
								headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
								headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
						.queryParameters(
								parameterWithName("chartType").description("결제 통계 차트 노출 기준(주단위,월단위)"))
						.responseFields(
								fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
								fieldWithPath("data.chartData[].label").type(JsonFieldType.STRING).description("결제 통계 차트에 노출명"),
								fieldWithPath("data.chartData[].totalAmount").type(JsonFieldType.NUMBER).description("결제 통계 총 금액"),
								fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보")
						)
						.build());
	}
}
