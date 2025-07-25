package kr.mywork.docs;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.member.controller.dto.request.MemberCreateWebRequest;
import kr.mywork.interfaces.member.controller.dto.request.MemberDeleteWebRequest;
import kr.mywork.interfaces.member.controller.dto.request.MemberUpdateWebRequest;
import kr.mywork.interfaces.member.controller.dto.request.ResetPasswordWebRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("멤버 삭제 테스트 성공")
	@Sql("classpath:sql/member-delete.sql")
	void 멤버_삭제_테스트_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();

		final UUID memberId = UUID.fromString("6516f3fe-057b-efdc-9aa9-87bf7b33a1d0");
		final MemberDeleteWebRequest memberDeleteWebRequest = new MemberDeleteWebRequest(memberId);

		final String requestBody = objectMapper.writeValueAsString(memberDeleteWebRequest);

		//when
		final ResultActions result = mockMvc.perform(delete("/api/member")
			.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));
		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(MockMvcRestDocumentationWrapper.document("memeber-delete-success", memberDeleteSuccessResource()));
	}

	private ResourceSnippet memberDeleteSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Member API")
				.summary("멤버 삭제 API")
				.description("멤버를 소프트 딜리트 한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("삭제된 멤버 아이디"),  // 수정
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))  // 수정
				.build());
	}

	@Test
	@DisplayName("멤버 생성 성공")
	void 멤버_생성_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();
		final UUID companyId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // UUID ver7
		final LocalDateTime birthDate = LocalDateTime.parse("2000-07-25T14:30:00");

		final MemberCreateWebRequest memberCreateWebRequest = new MemberCreateWebRequest(companyId, "김두만", "개발", "부장",
			"USER", "010-4040-5050", "eme@naver.com", birthDate);

		final String requestBody = objectMapper.writeValueAsString(memberCreateWebRequest);

		//when
		final ResultActions result = mockMvc.perform(
			post("/api/member")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(MockMvcRestDocumentationWrapper.document("member-create-success", memberCreateSuccessResource()));
	}

	private ResourceSnippet memberCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Member API")
				.summary("멤버 생성 API")
				.description("멤버 아이디를 생성한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("생성한 멤버 아이디"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("멤버 정보 업데이트 성공")
	@Sql("classpath:sql/member-update.sql")
	void 멤버_정보_업데이트_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();

		UUID memberId = UUID.fromString("6516f3fe-057b-efdc-9aa9-87bf7b33a1d0");
		UUID companyId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e");
		LocalDateTime birthDate = LocalDateTime.parse("2000-07-25T14:30:00");

		final MemberUpdateWebRequest memberUpdateWebRequest = new MemberUpdateWebRequest(memberId, companyId, "홍길동",
			"개발팀", "사원", "DEV_ADMIN", "010-1234-5633", "emwi@naver.com", true, birthDate);

		final String requestBody = objectMapper.writeValueAsString(memberUpdateWebRequest);

		//when
		final ResultActions result = mockMvc.perform(
			put("/api/member")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody));
		//then
		result.andExpectAll(status().isOk(), jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(), jsonPath("$.error").doesNotExist())
			.andDo(document("member-update-success", memberUpdateSuccessResource()));
	}

	private ResourceSnippet memberUpdateSuccessResource() {
		return resource(ResourceSnippetParameters.builder()
			.tag("Member API")
			.summary("멤버 수정 API")
			.description("전달 받은 정보로 멤버 정보를 수정한다.")
			.requestHeaders(headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
			.responseFields(fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
				fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("수정한 멤버 아이디"),
				fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
			.build());
	}

	@Test
	@DisplayName("멤버 조회 성공")
	@Sql("classpath:sql/member-search.sql")
	void 멤버_조회_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();

		//when
		final ResultActions result = mockMvc.perform(
			get("/api/member")
				.param("page","1")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));
		//then
		result.andExpectAll(status().isOk(), jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(), jsonPath("$.error").doesNotExist())
			.andDo(document("member-search-success", memberSearchSuccessResource()));
	}

	private ResourceSnippet memberSearchSuccessResource() {
		return resource(ResourceSnippetParameters.builder()
			.tag("Member API")
			.summary("멤버 조회 API")
			.description("전달 받은 정보로 멤버 조회한다.")
			.requestHeaders(headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
			.responseFields(fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
				fieldWithPath("data.members[].id").type(JsonFieldType.STRING).description("멤버 ID"),
				fieldWithPath("data.members[].name").type(JsonFieldType.STRING).description("멤버 이름"),
				fieldWithPath("data.members[].email").type(JsonFieldType.STRING).description("멤버 이메일"),
				fieldWithPath("data.members[].position").type(JsonFieldType.STRING).description("멤버 직급"),
				fieldWithPath("data.members[].department").type(JsonFieldType.STRING).description("멤버 부서"),
				fieldWithPath("data.members[].phoneNumber").type(JsonFieldType.STRING).description("멤버 전화번호"),
				fieldWithPath("data.members[].deleted").type(JsonFieldType.BOOLEAN).description("멤버 삭제여부"),
				fieldWithPath("data.members[].createdAt").type(JsonFieldType.STRING).description("멤버 생성일"),
				fieldWithPath("data.members[].companyId").type(JsonFieldType.STRING).description("멤버 회사아이디"),
				fieldWithPath("data.members[].companyName").type(JsonFieldType.STRING).description("멤버 회사이름"),
				fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("조회된 멤버 총 갯수"),
				fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
			.build());
	}

	@Test
	@DisplayName("멤버 상세 조회 성공")
	@Sql("classpath:sql/member-get-detail.sql")
	void 멤버_상세_조회_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();
		UUID memberId = UUID.fromString("51a58807-bf20-4160-b4e0-edabba6df8f9");

		//when
		final ResultActions result = mockMvc.perform(
			get("/api/member/{memberId}", memberId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));
		//then
		result.andExpectAll(status().isOk(), jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(), jsonPath("$.error").doesNotExist())
			.andDo(document("member-get-detail-success", memberDetailSuccessResource()));
	}

	private ResourceSnippet memberDetailSuccessResource() {
		return resource(ResourceSnippetParameters.builder()
			.tag("Member API")
			.summary("멤버 상세 조회 API")
			.description("멤버 상세정보를 조회한다.")
			.requestHeaders(headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
			.responseFields(fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
				fieldWithPath("data.companyId").type(JsonFieldType.STRING).description("멤버 회사ID"),
				fieldWithPath("data.companyName").type(JsonFieldType.STRING).description("멤버 회사이름"),
				fieldWithPath("data.name").type(JsonFieldType.STRING).description("멤버 이름"),
				fieldWithPath("data.department").type(JsonFieldType.STRING).description("멤버 부서"),
				fieldWithPath("data.position").type(JsonFieldType.STRING).description("멤버 지급"),
				fieldWithPath("data.role").type(JsonFieldType.STRING).description("멤버 권한"),
				fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("멤버 전화번호"),
				fieldWithPath("data.email").type(JsonFieldType.STRING).description("멤버 이메일"),
				fieldWithPath("data.deleted").type(JsonFieldType.BOOLEAN).description("멤버 삭제여부"),
				fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("멤버 수정일"),
				fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("멤버 생성일"),
				fieldWithPath("data.contactPhoneNumber").type(JsonFieldType.STRING).description("멤버 회사 연락처"),
				fieldWithPath("data.birthDate").type(JsonFieldType.STRING).description("멤버 생일"),
				fieldWithPath("data.projects[].projectId").type(JsonFieldType.STRING).description("멤버 참여중인 프로젝트 아이디"),
				fieldWithPath("data.projects[].projectName").type(JsonFieldType.STRING).description("멤버 참여중인 프로젝트 이름"),
				fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
			.build());
	}

	@Test
	@DisplayName("멤버 비밀 번호 변경 성공")
	@Sql("classpath:sql/member-password-change.sql")
	void 멤버_비밀번호_변경_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();
		ResetPasswordWebRequest request = new ResetPasswordWebRequest("admin@example.com","password1234","1234");
		final String requestBody = objectMapper.writeValueAsString(request);
		//when
		final ResultActions result = mockMvc.perform(
			post("/api/member/resetPassword")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody));
		//then
		result.andExpectAll(status().isOk(), jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(), jsonPath("$.error").doesNotExist())
			.andDo(document("member-password-change-success", memberPasswordChangeSuccessResource()));
	}

	private ResourceSnippet memberPasswordChangeSuccessResource() {
		return resource(ResourceSnippetParameters.builder()
			.tag("Member API")
			.summary("멤버 비밀번호 재설정 API")
			.description("멤버 비밀번호를 재설정 한다.")
			.requestHeaders(headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
			.responseFields(fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
				fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("비밀번호 변경된 멤버 Id"),
				fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
			.build());
	}

	@Test
	@DisplayName("멤버의 본인의 프로젝트 상세 조회")
	@Sql("classpath:sql/member-projects-get.sql")
	void 멤버_프로젝트_목록_조회_성공() throws Exception {
		//given
		final String accessToken = createUserAccessToken(); // system admin 의 권한이 통과해야 함.
		//when
		final ResultActions result = mockMvc.perform(
			get("/api/member/{memberId}/myProjects", UUID.fromString("6939d8be-1bf2-4f01-9189-12864e38d913"))
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(status().isOk(), jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(), jsonPath("$.error").doesNotExist())
			.andDo(document("member-projects-get-success", memberProjectsGetSuccessResource()));
	}

	private ResourceSnippet memberProjectsGetSuccessResource() {
		return resource(ResourceSnippetParameters.builder()
			.tag("Member API")
			.summary("멤버 프로젝트 리스트 조회 API")
			.description("멤버 프로젝트 리스트 조회 한다.")
			.requestHeaders(headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
			.responseFields(fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
				fieldWithPath("data.projects[].projectId").type(JsonFieldType.STRING).description("프로젝트 Id"),
				fieldWithPath("data.projects[].projectName").type(JsonFieldType.STRING).description("프로젝트 이름"),
				fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
			.build());
	}
}
