package kr.mywork.docs;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippetParameters;

public class CheckListHistoryDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("체크리스트 히스토리 목록 조회 성공")
	@Sql("classpath:sql/check-list-history-select.sql")
	void 체크리스트_히스토리_목록_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();
		final UUID checkListId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // 예시 UUID

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/projects/checkLists/{checkListId}/histories", checkListId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value("SUCCESS"),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("check-list-history-list-success",
				resource(
					ResourceSnippetParameters.builder()
						.tag("CheckListHistory API")
						.summary("체크리스트 히스토리 목록 조회 API")
						.description("특정 체크리스트의 히스토리 목록을 조회한다.")
						.requestHeaders(
							headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
							headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
						.pathParameters(
							parameterWithName("checkListId").description("체크리스트 ID"))
						.responseFields(
							fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
							fieldWithPath("data[].historyId").type(JsonFieldType.STRING).description("체크리스트 히스토리 ID"),
							fieldWithPath("data[].approval").type(JsonFieldType.STRING).description("승인 상태"),
							fieldWithPath("data[].reason").type(JsonFieldType.STRING).description("사유"),
							fieldWithPath("data[].companyName").type(JsonFieldType.STRING).description("회사 이름"),
							fieldWithPath("data[].memberName").type(JsonFieldType.STRING).description("유저 이름"),
							fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("생성 일시"),
							fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
						.build())));
	}
}
