package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import kr.mywork.interfaces.post.controller.dto.request.ReviewCreateWebRequest;

public class ReviewDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("리뷰 생성 테스트 성공")
	void 리뷰_생성_테스트_성공() throws Exception {
		// given
		final UUID postId = UUID.fromString("01972f9b-232a-7dbe-aad2-3bffc0b78ced");

		final ReviewCreateWebRequest reviewCreateWebRequest = new ReviewCreateWebRequest(
			postId, "코멘트01", null);

		final String requestBody = objectMapper.writeValueAsString(reviewCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/posts/reviews")
			.contentType(MediaType.APPLICATION_JSON)
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
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postId").type(JsonFieldType.STRING).description("생성된 리뷰의 게시글 아이디"),
					fieldWithPath("data.parentId").type(JsonFieldType.NULL).description("리뷰의 부모 아이디"),
					fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("작성자 아이디"),
					fieldWithPath("data.comment").type(JsonFieldType.STRING).description("코멘트 내용"),
					fieldWithPath("data.authorName").type(JsonFieldType.STRING).description("작성자 이름"),
					fieldWithPath("data.companyName").type(JsonFieldType.STRING).description("회사 이름"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
}
