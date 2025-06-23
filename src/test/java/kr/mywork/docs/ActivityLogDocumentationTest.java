package kr.mywork.docs;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import kr.mywork.common.api.support.response.ResultType;

public class ActivityLogDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("활동 로그 조회 테스트 성공")
	@Sql("classpath:sql/activity-log-get-list.sql")
	void 활동_로그_목록_조회_테스트_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();

		//when
		final ResultActions result = mockMvc.perform(
			get("/api/activity-logs")
				.param("page", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(MockMvcRestDocumentation.document("activity-log-list-get-success", ActivityLogListGetSuccess()));
	}

	private ResourceSnippet ActivityLogListGetSuccess() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("ActivityLog API")
				.summary("활동 로그 목록 조회 API")
				.description("활동 로그 목록을 조회한다.")
				.queryParameters(
					parameterWithName("page").description("페이지 번호"))
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("총 개수"),
					fieldWithPath("data.activityLogSelectWebResponses[].actionTime").type(JsonFieldType.STRING).description("액션 시간"),
					fieldWithPath("data.activityLogSelectWebResponses[].actionType").type(JsonFieldType.STRING).description("액션 유형"),
					fieldWithPath("data.activityLogSelectWebResponses[].targetType").type(JsonFieldType.STRING).description("액션 대상 종류"),
					fieldWithPath("data.activityLogSelectWebResponses[].actorName").type(JsonFieldType.STRING).description("액션한 사용자명"),
					fieldWithPath("data.activityLogSelectWebResponses[].actorCompanyName").type(JsonFieldType.STRING).description("액션한 사용자 회사명"),

					fieldWithPath("data.activityLogSelectWebResponses[].logDetails").description("로그 상세 목록"),
					fieldWithPath("data.activityLogSelectWebResponses[].logDetails[].fieldType").type(JsonFieldType.STRING).description("변경 필드명"),
					fieldWithPath("data.activityLogSelectWebResponses[].logDetails[].oldValue").type(JsonFieldType.STRING).description("변경 전 값"),
					fieldWithPath("data.activityLogSelectWebResponses[].logDetails[].newValue").type(JsonFieldType.STRING).description("변경 후 값"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}
}
