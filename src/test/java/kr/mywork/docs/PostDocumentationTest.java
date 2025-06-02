package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.fasterxml.uuid.Generators;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.post.controller.dto.request.PostCreateWebRequest;

public class PostDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("게시글 ID 생성 테스트")
	void 게시글_아이디_생성_테스트_성공() throws Exception {

		//given, when
		ResultActions result = mockMvc.perform(
			post("/api/posts/id/generate")
				.contentType(MediaType.APPLICATION_JSON));

		//then
		result.andExpectAll(
			status().isOk(),
			jsonPath("$.result").value(ResultType.SUCCESS.name()),
			jsonPath("$.data").exists(),
			jsonPath("$.error").doesNotExist()
		).andDo(document("post-id-create-success", postIdCreateSuccessResource()));
	}

	private ResourceSnippet postIdCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 아이디 API")
				.description("게시글 아이디를 발급 받는다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postId").type(JsonFieldType.STRING).description("발급받은 게시글 생성 아이디"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("게시글 생성 성공")
	@Sql("classpath:sql/post-id.sql")
	void 게시글_생성_성공() throws Exception {
		// given
		UUID postId = UUID.fromString("1234a9a9-90b6-9898-a9dc-92c9861aa98c"); // UUID ver7
		UUID projectStepId = UUID.fromString("4321a2a2-00b2-0000-c2bb-81c0000aa00c"); // UUID ver7

		final PostCreateWebRequest postCreateWebRequest =
			new PostCreateWebRequest(postId, projectStepId, "게시글 제목", "게시글 이름", "저자 이름", "게시글 내용");

		final String requestBody = objectMapper.writeValueAsString(postCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/posts") // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-create-success", postCreateSuccessResource()));
	}

	private ResourceSnippet postCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 생성 API")
				.description("발급받은 게시글 아이디를 통해 게시글를 생성한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postId").type(JsonFieldType.STRING).description("생성한 게시글 아이디"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("게시글 생성 실패 - 아이디가 존재하지 않는 경우")
	void 게시글_생성_실패_아이디_미존재() throws Exception {
		// given
		UUID postId = Generators.timeBasedEpochGenerator().generate(); // UUID ver7
		UUID projectStepId = UUID.fromString("4321a2a2-00b2-0000-c2bb-81c0000aa00c"); // UUID ver7

		final PostCreateWebRequest postCreateWebRequest =
			new PostCreateWebRequest(postId, projectStepId, "게시글 제목", "게시글 이름", "저자 이름", "게시글 내용");

		final String requestBody = objectMapper.writeValueAsString(postCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/posts") // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));

		// then
		result.andExpectAll(
				status().is4xxClientError(),
				jsonPath("$.result").value(ResultType.ERROR.name()),
				jsonPath("$.data").doesNotExist(),
				jsonPath("$.error").exists())
			.andDo(document("post-create-fail01", postCreateFailResource01()));
	}

	private ResourceSnippet postCreateFailResource01() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 생성 API")
				.description("발급받은 게시글 아이디를 통해 게시글를 생성한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data").type(JsonFieldType.NULL).description("응답 데이터"),
					fieldWithPath("error.code").type(JsonFieldType.STRING).description("에러 코드"),
					fieldWithPath("error.message").type(JsonFieldType.STRING).description("에러 정보"),
					fieldWithPath("error.data").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

}
