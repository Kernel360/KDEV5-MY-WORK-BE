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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.company.controller.dto.request.CompanyImageUploadUrlIssueWebRequest;

public class CompanyImageDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("회사 이미지 업로드 URL 발급 성공")
	@Sql("classpath:/sql/company-image-issue.sql")
	void 회사_이미지_업로드_URL_발급_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();
		final CompanyImageUploadUrlIssueWebRequest request = new CompanyImageUploadUrlIssueWebRequest(
			UUID.fromString("01979cb6-06ee-7d85-bf73-6be6ffea4e6f"), "test.png"
		);
		final String requestBody = objectMapper.writeValueAsString(request);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/companies/images/upload-url/issue")
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
		);

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("company-image-upload-url-issue-success", companyImageUploadUrlIssueResource()));
	}

	@Test
	@DisplayName("회사 이미지 다운로드 URL 발급 성공")
	@Sql("classpath:/sql/company-image-download.sql")
	void 회사_이미지_다운로드_URL_발급_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();
		final UUID companyId = UUID.fromString("01979cb6-d462-77fd-9e59-2829f4b59321");

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/companies/images/download-url?companyId={companyId}", companyId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data.companyId").exists(),
				jsonPath("$.data.downloadUrl").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("company-image-download-url-issue-success", companyImageDownloadUrlIssueResource()));
	}

	private ResourceSnippet companyImageDownloadUrlIssueResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company Image API")
				.summary("회사 이미지 다운로드 URL 발급")
				.description("회사 이미지 다운로드용 presigned URL을 발급한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"),
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텍츠 타입"))
				.responseFields(
					fieldWithPath("result").description("응답 결과"),
					fieldWithPath("data.companyId").description("회사 ID"),
					fieldWithPath("data.downloadUrl").description("다운로드 presigned URL"),
					fieldWithPath("error").description("에러 정보").optional())
				.build());
	}

	@Test
	@DisplayName("회사 이미지 삭제 성공")
	@Sql("classpath:/sql/company-image-delete.sql")
	@Disabled
	void 회사_이미지_삭제_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();
		final UUID companyId = UUID.fromString("01979cb6-d462-77fd-9e59-2829f4b59321");

		// when
		final ResultActions result = mockMvc.perform(
			delete("/api/companies/images/{companyId}", companyId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data.companyId").exists(),
				jsonPath("$.data.deleted").value(true),
				jsonPath("$.error").doesNotExist())
			.andDo(document("company-image-delete-success", companyImageDeleteResource()));
	}

	private ResourceSnippet companyImageUploadUrlIssueResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company Image API")
				.summary("회사 이미지 업로드 URL 발급")
				.description("회사 이미지 업로드용 presigned URL을 발급한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.requestFields(
					fieldWithPath("companyId").description("회사 ID"),
					fieldWithPath("fileName").description("업로드할 파일명"))
				.responseFields(
					fieldWithPath("result").description("응답 결과"),
					fieldWithPath("data.companyId").description("회사 ID"),
					fieldWithPath("data.uploadUrl").description("업로드 presigned URL"),
					fieldWithPath("error").description("에러 정보").optional())
				.build()
		);
	}

	private ResourceSnippet companyImageDeleteResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company Image API")
				.summary("회사 이미지 삭제")
				.description("회사 이미지를 삭제한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.pathParameters(
					parameterWithName("companyId").description("회사 ID"))
				.responseFields(
					fieldWithPath("result").description("응답 결과"),
					fieldWithPath("data.companyId").description("회사 ID"),
					fieldWithPath("data.deleted").description("삭제 성공 여부"),
					fieldWithPath("error").description("에러 정보").optional())
				.build()
		);
	}
}
