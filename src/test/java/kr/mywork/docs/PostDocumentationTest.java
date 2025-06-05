package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.uuid.Generators;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.post.controller.dto.request.PostCreateWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostUpdateWebRequest;

public class PostDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("게시글 ID 생성 테스트")
	void 게시글_아이디_생성_테스트_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		// when
		ResultActions result = mockMvc.perform(
			post("/api/posts/id/generate")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

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
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
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
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 게시글_생성_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		UUID postId = UUID.fromString("1234a9a9-90b6-9898-a9dc-92c9861aa98c"); // UUID ver7
		UUID projectStepId = UUID.fromString("4321a2a2-00b2-0000-c2bb-81c0000aa00c"); // UUID ver7
		final PostCreateWebRequest postCreateWebRequest =
			new PostCreateWebRequest(postId, projectStepId, "게시글 제목", "게시글 이름", "저자 이름", "게시글 내용");

		final String requestBody = objectMapper.writeValueAsString(postCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/posts") // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
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
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰")
					)
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
		final String accessToken = createUserAccessToken();

		UUID postId = Generators.timeBasedEpochGenerator().generate(); // UUID ver7
		UUID projectStepId = UUID.fromString("4321a2a2-00b2-0000-c2bb-81c0000aa00c"); // UUID ver7

		final PostCreateWebRequest postCreateWebRequest =
			new PostCreateWebRequest(postId, projectStepId, "게시글 제목", "게시글 이름", "저자 이름", "게시글 내용");

		final String requestBody = objectMapper.writeValueAsString(postCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/posts") // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
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
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data").type(JsonFieldType.NULL).description("응답 데이터"),
					fieldWithPath("error.code").type(JsonFieldType.STRING).description("에러 코드"),
					fieldWithPath("error.message").type(JsonFieldType.STRING).description("에러 정보"),
					fieldWithPath("error.data").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("게시글 수정 성공")
	@Sql("classpath:sql/post-for-update.sql")
	void 게시글_수정_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();
		UUID postId = UUID.fromString("1234a9a9-90b6-9898-a9dc-92c9861aa98c"); // UUID ver7

		final PostUpdateWebRequest postUpdateWebRequest =
			new PostUpdateWebRequest("바뀐 제목", "바뀐 컨텐츠");

		final String requestBody = objectMapper.writeValueAsString(postUpdateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			put("/api/posts/{postId}", postId) // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-update-success", postUpdateSuccessResource()));
	}

	private ResourceSnippet postUpdateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 수정 API")
				.description("게시글의 title, content를 수정한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postId").type(JsonFieldType.STRING).description("수정한 게시글 아이디"),
					fieldWithPath("data.title").type(JsonFieldType.STRING).description("수정한 게시글 제목"),
					fieldWithPath("data.content").type(JsonFieldType.STRING).description("수정한 게시글 내용"),
					fieldWithPath("data.companyName").type(JsonFieldType.STRING).description("회사 이름"),
					fieldWithPath("data.authorName").type(JsonFieldType.STRING).description("작성자"),
					fieldWithPath("data.approval").type(JsonFieldType.STRING).description("승인여부"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

   @Test
    @DisplayName("게시글 단건 조회 성공")
    @Sql("classpath:sql/post-for-get.sql")
    void 게시글_단건_조회_성공() throws Exception {
        // given
        UUID postId = UUID.fromString("1234a9a9-90b6-9898-a9dc-92c9861aa98c"); // UUID ver7

        // when
        final ResultActions result = mockMvc.perform(
            get("/api/posts/{postId}", postId) // HTTP method (URL)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpectAll(
                status().isOk(),
                jsonPath("$.result").value(ResultType.SUCCESS.name()),
                jsonPath("$.data").exists(),
                jsonPath("$.error").doesNotExist())
            .andDo(document("post-get-success", postGetSuccessResource()));
    }

    private ResourceSnippet postGetSuccessResource() {
        return resource(
            ResourceSnippetParameters.builder()
                .tag("Post API")
                .summary("게시글 단건 조회 API")
                .description("게시글 ID로 조회를 한다.")
                .requestHeaders(
                    headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
                .responseFields(
                    fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
                    fieldWithPath("data.postId").type(JsonFieldType.STRING).description("게시글 아이디"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING).description("게시글 제목"),
                    fieldWithPath("data.content").type(JsonFieldType.STRING).description("게시글 내용"),
                    fieldWithPath("data.companyName").type(JsonFieldType.STRING).description("회사 이름"),
                    fieldWithPath("data.authorName").type(JsonFieldType.STRING).description("작성자"),
                    fieldWithPath("data.approval").type(JsonFieldType.STRING).description("승인여부"),
                    fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
                .build()
        );
    }    
   
	@Test
	@DisplayName("게시글 삭제 성공")
	@Sql("classpath:sql/post-for-delete.sql")
	void 게시글_삭제_성공() throws Exception {
		// given
		UUID postId = UUID.fromString("1234a9a9-90b6-9898-a9dc-92c9861aa98c"); // UUID ver7

		// when
		final ResultActions result = mockMvc.perform(
			delete("/api/posts/{postId}", postId) // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-delete-success", postDeleteSuccessResource()));
	}

	private ResourceSnippet postDeleteSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 삭제 API")
				.description("게시글을 삭제한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postId").type(JsonFieldType.STRING).description("수정한 게시글 아이디"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}
        
	@Test
	@DisplayName("게시글 목록 조회 성공")
	@Sql("classpath:sql/post-for-get-list.sql")
	void 게시글_목록_조회_성공() throws Exception {
		// given
		UUID projectStepId = UUID.fromString("019739d2-2e80-709f-a9c5-7da758c956d1");

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/posts?page={page}&projectStepId={projectStepId}&keyword={keyword}&deleted={deleted}",
				1, projectStepId, null, null) // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-get-list-success", postGetListSuccessResource()));
	}

	private ResourceSnippet postGetListSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 목록 조회 API")
				.description("게시글 목록을 검색조건에 따라 조회를 한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.queryParameters(
					parameterWithName("page").description("페이지 번호"),
					parameterWithName("projectStepId").description("프로젝트 단계 ID").optional(),
					parameterWithName("keyword").description("검색어").optional(),
					parameterWithName("deleted").description("삭제 여부").optional()
				)
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.posts.[].postId").type(JsonFieldType.STRING).description("게시글 아이디"),
					fieldWithPath("data.posts.[].authorName").type(JsonFieldType.STRING).description("작성자"),
					fieldWithPath("data.posts.[].title").type(JsonFieldType.STRING).description("게시글 제목"),
					fieldWithPath("data.posts.[].createdAt").type(JsonFieldType.STRING).description("생성 일자"),
					fieldWithPath("data.posts.[].approval").type(JsonFieldType.STRING).description("승인여부"),
					fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("총 갯수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}
}
