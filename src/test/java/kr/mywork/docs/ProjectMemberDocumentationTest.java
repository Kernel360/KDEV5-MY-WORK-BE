package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
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

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import kr.mywork.common.api.support.response.ResultType;

public class ProjectMemberDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("프로젝트 맴버 할당 성공")
	void 프로젝트_멤버_할당_성공() throws Exception {
		// given
		// TODO Project 생성 API 개발 후, ProjectId 생성 및 검증 샘플 데이터 추가 필요
		final String accessToken = createDevAdminAccessToken();

		final UUID projectId = UUID.fromString("0197207e-7331-7000-946b-a29a79a82424");
		final UUID memberId = UUID.fromString("0ddcc36d-193e-41f4-b48e-8b77d5d19162");

		// when
		final ResultActions result = mockMvc.perform(post("/api/project-member/member")
				.param("projectId", projectId.toString())
				.param("memberId", memberId.toString())
			.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
			.contentType(MediaType.APPLICATION_JSON));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-id-create-success", projectIdCreateSuccessResource()));
	}

	private ResourceSnippet projectIdCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project Member API")
				.summary("프로젝트 멤버 할당 API")
				.description("프로젝트 멤버를 할당한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("할당한 멤버의 ID"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
	@Test
	@DisplayName("프로젝트 할당된 멤버 조회 성공")
	@Sql("classpath:sql/project-member-get.sql")
	void 프로젝트_멤버_조회_성공() throws Exception {
		// given
		final String accessToken = createDevAdminAccessToken();

		final UUID projectId = UUID.fromString("01974f0b-5c7a-7fa2-9aba-1323490b77e9");
		final UUID companyId = UUID.fromString("6939d8be-1bf2-4f01-9189-12864e38d913");

		// when
		final ResultActions result = mockMvc.perform(get("/api/project-member")
			.param("projectId", projectId.toString())
			.param("companyId", companyId.toString())
			.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
			.contentType(MediaType.APPLICATION_JSON));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-member-get-success", projectMemberGetSuccessResource()));
	}

	private ResourceSnippet projectMemberGetSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project Member API")
				.summary("프로젝트 멤버 조회 API")
				.description("프로젝트 멤버를 조회한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.members[].memberId").type(JsonFieldType.STRING).description("프로젝트 멤버 아이디"),
					fieldWithPath("data.members[].memberName").type(JsonFieldType.STRING).description("프로젝트 멤버 이름"),
					fieldWithPath("data.members[].email").type(JsonFieldType.STRING).description("프로젝트 멤버 이메일"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
}
