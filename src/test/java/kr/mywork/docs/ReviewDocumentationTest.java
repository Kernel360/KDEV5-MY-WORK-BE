package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.context.jdbc.Sql;
import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.post.controller.dto.request.ReviewCreateWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.ReviewModifyWebRequest;

public class ReviewDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("리뷰 생성 테스트 성공")
	// @Sql("classpath:sql/member-test-users.sql")
	void 리뷰_생성_테스트_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		final UUID postId = UUID.fromString("01972f9b-232a-7dbe-aad2-3bffc0b78ced");

		final ReviewCreateWebRequest reviewCreateWebRequest = new ReviewCreateWebRequest(
			postId, "코멘트01", null);

		final String requestBody = objectMapper.writeValueAsString(reviewCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/reviews")
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
			.content(requestBody));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("review-create-success", reviewCreateSuccessResource()));
	}

	private ResourceSnippet reviewCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Review API")
				.summary("리뷰 생성 API")
				.description("리뷰를 생성한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.reviewId").type(JsonFieldType.STRING).description("생성된 리뷰 아이디"),
					fieldWithPath("data.reviewParentId").type(JsonFieldType.NULL).description("리뷰의 부모 아이디"),
					fieldWithPath("data.comment").type(JsonFieldType.STRING).description("코멘트 내용"),
					fieldWithPath("data.authorName").type(JsonFieldType.STRING).description("작성자 이름"),
					fieldWithPath("data.companyName").type(JsonFieldType.STRING).description("회사 이름"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("리뷰 수정 테스트 성공")
	@Sql("classpath:sql/review-modify.sql")
	void 리뷰_수정_테스트_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		final UUID reviewId = UUID.fromString("0197385a-eda5-7e17-a3ce-4252908f8d1f");

		final ReviewModifyWebRequest reviewModifyWebRequest = new ReviewModifyWebRequest("코멘트01_수정");

		final String requestBody = objectMapper.writeValueAsString(reviewModifyWebRequest);

		// when
		final ResultActions result = mockMvc.perform(put("/api/reviews/{reviewId}", reviewId)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
			.content(requestBody));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("review-modify-success", reviewModifySuccessResource()));
	}

	private ResourceSnippet reviewModifySuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Review API")
				.summary("리뷰 수정 API")
				.description("리뷰를 수정한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.reviewId").type(JsonFieldType.STRING).description("수정된 리뷰 아이디"),
					fieldWithPath("data.comment").type(JsonFieldType.STRING).description("수정된 리뷰 글"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("리뷰 삭제 테스트 성공")
	@Sql("classpath:sql/review-delete.sql")
	void 리뷰_삭제_테스트_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		final UUID memberId = UUID.fromString("01973844-b287-73d0-8f9d-f86fad4ac4c3");
		final UUID reviewId = UUID.fromString("0197385a-eda5-7e17-a3ce-4252908f8d1f");

		// when
		final ResultActions result = mockMvc.perform(delete("/api/reviews/{reviewId}", reviewId)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("review-delete-success", reviewDeleteSuccessResource()));
	}

	private ResourceSnippet reviewDeleteSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Review API")
				.summary("리뷰 삭제 API")
				.description("리뷰를 삭제한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.reviewId").type(JsonFieldType.STRING).description("삭제된 리뷰 아이디"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
}
