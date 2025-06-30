package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;
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
import kr.mywork.interfaces.post.controller.dto.request.PostAttachmentActiveWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostAttachmentUploadUrlIssueWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostAttachmentUploadUrlReissueWebRequest;

public class PostAttachmentDocumentation extends RestDocsDocumentation {

	@Test
	@DisplayName("파일 업로드 URL 발급 성공")
	@Sql("classpath:sql/post-attachment-upload-issue.sql")
	void 파일_업로드_URL_발급_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		final UUID postId = UUID.fromString("019790cd-b39a-72d6-b3a3-403250b68b9e");
		final String fileName = "mywork-image.jpg";
		final PostAttachmentUploadUrlIssueWebRequest postAttachmentUploadUrlIssueWebRequest =
			new PostAttachmentUploadUrlIssueWebRequest(postId, fileName);

		final String requestBody = objectMapper.writeValueAsString(postAttachmentUploadUrlIssueWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/posts/attachment/upload-url/issue")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-attachment-upload-issue-success", postAttachmentUploadUrlIssueResource()));
	}

	private ResourceSnippet postAttachmentUploadUrlIssueResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 파일 업로드 URL 발급 API")
				.description("게시글 파일 업로드을 위한 Presigned URL 을 발급받는다. (유효시간: 3분)")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postAttachmentId").type(JsonFieldType.STRING).description("게시글 첨부파일 ID"),
					fieldWithPath("data.uploadUrl").type(JsonFieldType.STRING).description("게시글 파일 업로드 URL"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("파일 업로드 URL 재발급 성공")
	@Sql("classpath:sql/post-attachment-upload-reissue.sql")
	void 파일_업로드_URL_재발급_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		final UUID postAttachmentId = UUID.fromString("019790db-3830-768d-83ea-a57eeee6bbfc");
		final String fileName = "mywork-image.jpg";
		final PostAttachmentUploadUrlReissueWebRequest postAttachmentUploadUrlIssueWebRequest =
			new PostAttachmentUploadUrlReissueWebRequest(postAttachmentId, fileName);

		final String requestBody = objectMapper.writeValueAsString(postAttachmentUploadUrlIssueWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/posts/attachment/upload-url/reissue")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-attachment-reupload-success", postAttachmentUploadUrlReissueResource()));
	}

	private ResourceSnippet postAttachmentUploadUrlReissueResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 파일 재업로드 URL 발급 API")
				.description("게시글 파일 업로드을 위한 Presigned URL 을 재발급받는다. (유효시간: 3분)")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postAttachmentId").type(JsonFieldType.STRING).description("게시글 첨부파일 ID"),
					fieldWithPath("data.uploadUrl").type(JsonFieldType.STRING).description("게시글 파일 재업로드 URL"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("파일 업로드 완료 상태 변경 성공")
	@Sql("classpath:sql/post-attachment-upload-active.sql")
	void 파일_업로드_완료_상태_변경_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		final UUID postId = UUID.fromString("019790da-3d89-7d84-b5e1-b5bb8109dc02");
		final boolean active = true;
		final PostAttachmentActiveWebRequest postAttachmentActiveWebRequest =
			new PostAttachmentActiveWebRequest(postId, active);

		final String requestBody = objectMapper.writeValueAsString(postAttachmentActiveWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/posts/attachment/active")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-attachment-active-success", postAttachmentActiveResource()));
	}

	private ResourceSnippet postAttachmentActiveResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 파일 업로드 완료 상태 변경 API 개발")
				.description("게시글 파일 업로드 완료 상태를 변경한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postAttachments").type(JsonFieldType.ARRAY).description("게시글 첨부파일 활성화 목록"),
					fieldWithPath("data.postAttachments[].postAttachmentId").type(JsonFieldType.STRING)
						.description("게시글 첨부파일 ID"),
					fieldWithPath("data.postAttachments[].active").type(JsonFieldType.BOOLEAN)
						.description("게시글 첨부파일 활성화 여부"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("파일 다운로드 URL 발급 성공")
	@Sql("classpath:sql/post-attachment-download.sql")
	void 파일_다운로드_URL_발급_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();

		final UUID postAttachmentId = UUID.fromString("019790db-3830-768d-83ea-a57eeee6bbfc");

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/posts/attachment/download-url?postAttachmentId={postAttachmentId}", postAttachmentId.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-attachment-download-url-success", postAttachmentDownloadUrlResource()));
	}

	private ResourceSnippet postAttachmentDownloadUrlResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 파일 다운로드 URL 발급 API")
				.description("게시글 파일 다운로드 URL을 발급한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postAttachmentId").type(JsonFieldType.STRING).description("게시글 첨부파일 ID"),
					fieldWithPath("data.downloadUrl").type(JsonFieldType.STRING).description("첨부파일 다운로드 URL"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("게시글 첨부파일 삭제 성공")
	@Disabled
	@Sql("classpath:sql/post-attachment-delete.sql")
	void 게시글_첨부파일_삭제_성공() throws Exception {
		// given
		final String accessToken = createUserAccessToken();
		final UUID postAttachmentId = UUID.fromString("019790db-3830-768d-83ea-a57eeee6bbfc");

		// when
		final ResultActions result = mockMvc.perform(
			delete("/api/posts/attachment/{postAttachmentId}", postAttachmentId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data.postAttachmentId").value(postAttachmentId.toString()),
				jsonPath("$.error").doesNotExist())
			.andDo(document("post-attachment-delete-success", postAttachmentDeleteResource()));
	}

	private ResourceSnippet postAttachmentDeleteResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Post API")
				.summary("게시글 파일 삭제 API")
				.description("게시글에 첨부된 파일을 삭제한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.pathParameters(
					parameterWithName("postAttachmentId").description("삭제할 게시글 첨부파일 ID"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.postAttachmentId").type(JsonFieldType.STRING).description("삭제된 게시글 첨부파일 ID"),
					fieldWithPath("data.deleted").type(JsonFieldType.BOOLEAN).description("게시글 첨부파일 삭제 여부"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}
}
