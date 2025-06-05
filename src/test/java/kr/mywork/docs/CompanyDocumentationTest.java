package kr.mywork.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
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
import kr.mywork.interfaces.company.controller.dto.request.CompanyCreateWebRequest;
import kr.mywork.interfaces.company.controller.dto.request.CompanyDeleteWebRequest;
import kr.mywork.interfaces.company.controller.dto.request.CompanyUpdateWebRequest;

public class CompanyDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("회사 아이디 생성 테스트 성공")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 회사_아이디_생성_테스트_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/company/id/generate")
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.contentType(MediaType.APPLICATION_JSON));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("company-id-create-success", companyIdCreateSuccessResource()));
	}

	private ResourceSnippet companyIdCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company API")
				.summary("회사 아이디 API")
				.description("회사 아이디를 발급 받는다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.companyId").type(JsonFieldType.STRING).description("발급받은 회사 생성 아이디"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@WithMockUser(roles = "SYSTEM_ADMIN")
	@DisplayName("회사 생성 성공")
	@Sql("classpath:sql/company-id.sql")
	void 회사_생성_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		UUID companyId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // UUID ver7

		final CompanyCreateWebRequest companyCreateWebRequest =
			new CompanyCreateWebRequest(companyId, "회사 이름", "회사 디테일", "0123-123", "강남 밀왈빌딩", "DEV", "010-0000-0000",
				"company01@gmail.com", "/image/url");

		final String requestBody = objectMapper.writeValueAsString(companyCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/company") // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("company-create-success", companyCreateSuccessResource()));
	}

	private ResourceSnippet companyCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company API")
				.summary("회사 생성 API")
				.description("발급받은 회사 아이디를 통해 회사를 생성한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰")
				)
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.companyId").type(JsonFieldType.STRING).description("생성한 회사 아이디"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("회사 생성 실패 - 아이디가 존재하지 않는 경우")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 회사_생성_실패_아이디_미존재() throws Exception {
		// given
		UUID companyId = Generators.timeBasedEpochGenerator().generate(); // UUID ver7

		final CompanyCreateWebRequest companyCreateWebRequest =
			new CompanyCreateWebRequest(companyId, "회사 이름", "회사 디테일", "0123-123", "강남 밀왈빌딩", "DEV", "010-0000-0000",
				"company01@gmail.com", "/image/url");

		final String requestBody = objectMapper.writeValueAsString(companyCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/company") // HTTP method (URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));

		// then
		result.andExpectAll(
				status().is4xxClientError(),
				jsonPath("$.result").value(ResultType.ERROR.name()),
				jsonPath("$.data").doesNotExist(),
				jsonPath("$.error").exists())
			.andDo(document("company-create-fail01", companyCreateFailResource01()));
	}

	private ResourceSnippet companyCreateFailResource01() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company API")
				.summary("회사 생성 API")
				.description("발급받은 회사 아이디를 통해 회사를 생성한다.")
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

	@Test
	@DisplayName("회사 정보 업데이트 성공")
	@Sql("classpath:sql/company-for-update.sql")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 회사_정보_업데이트_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();
		final UUID id = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e");

		final CompanyUpdateWebRequest companyUpdateWebRequest = new CompanyUpdateWebRequest(id, "현대", "바뀐회사설명",
			"010234034", "부산", "DEV", "010-9999-9999", "suha730@naver.com", "/image/url");

		final String requestBody = objectMapper.writeValueAsString(companyUpdateWebRequest);

		//when
		final ResultActions result = mockMvc.perform(
			put("/api/company")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody)
		);

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("company-update-success", companyUpdateSuccessResource()));

	}

	private ResourceSnippet companyUpdateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company API")
				.summary("회사 업데이트 API")
				.description("새로운 회사 정보로 업데이트한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰")
				)
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.companyId").type(JsonFieldType.STRING).description("업데이트된 회사 아이디"),  // 수정
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))  // 수정
				.build()
		);
	}

	@Test
	@DisplayName("회사 정보 업데이트 실패 - 잘못되 요청값(존재하지 않는 회사 타입 요청)")
	@Sql("classpath:sql/company-for-update.sql")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 회사_정보_업데이트_실패() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();

		final UUID id = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e");

		final CompanyCreateWebRequest companyCreateWebRequest = new CompanyCreateWebRequest(id, "삼성",
			"회사 타입을 존재하지 않는 타입으로 변경 요청",
			"010234034", "부산", "INVALID_TYPE", "010-9999-9999", "suha730@naver.com", "/image/url");

		final String requestBody = objectMapper.writeValueAsString(companyCreateWebRequest);

		//when
		final ResultActions result = mockMvc.perform(
			put("/api/company")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody)
		);

		//then
		result.andExpectAll(
				status().is4xxClientError(),
				jsonPath("$.result").value(ResultType.ERROR.name()),
				jsonPath("$.data").doesNotExist(),
				jsonPath("$.error").exists())
			.andDo(document("company-update-fail", companyUpdateFailResource()));
	}

	private ResourceSnippet companyUpdateFailResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company API")
				.summary("회사 업데이트 API")
				.description("새로운 회사 정보로 업데이트한다.")
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
	@DisplayName("회사 삭제 성공")
	@Sql("classpath:sql/company-delete.sql")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 회사_삭제_성공() throws Exception {
		final String accessToken = createSystemAccessToken();

		UUID companyId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // company-id.sql과 동일한 값
		CompanyDeleteWebRequest deleteReq = new CompanyDeleteWebRequest(companyId);

		// JSON 요청 본문 생성
		String requestBody = objectMapper.writeValueAsString(deleteReq);

		// When
		ResultActions result = mockMvc.perform(
			delete("/api/company", companyId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody)
		);

		// Then
		result.andExpectAll(
			status().isOk(),
			jsonPath("$.result").value(ResultType.SUCCESS.name()),
			jsonPath("$.data.companyId").value(companyId.toString()),
			jsonPath("$.error").doesNotExist()
		).andDo(document("company-del-success", companyDeleteSuccess()));
	}

	private ResourceSnippet companyDeleteSuccess() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company API")
				.summary("회사 삭제 API")
				.description("발급받은 회사 아이디를 통해 회사를 삭제한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.companyId").type(JsonFieldType.STRING).description("삭제한 회사 아이디"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("회사 상세 조회 성공")
	@Sql("classpath:sql/company-detail.sql")
	void 회사_상세_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		UUID companyId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // company-id.sql과 동일한 값

		// When
		ResultActions result = mockMvc.perform(get("/api/company/{companyId}", companyId)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
			.accept(MediaType.APPLICATION_JSON));

		// Then
		result.andExpectAll(
			status().isOk(),
			jsonPath("$.result").value(ResultType.SUCCESS.name()),
			jsonPath("$.data").exists(),
			jsonPath("$.error").doesNotExist()
		).andDo(document("company-del-success", companyDetailSuccess()));
	}

	private ResourceSnippet companyDetailSuccess() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company API")
				.summary("회사 상세조회 API")
				.description("회사 상세정보를 조회한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.companyId").type(JsonFieldType.STRING).description("회사 ID"),
					fieldWithPath("data.name").type(JsonFieldType.STRING).description("회사명"),
					fieldWithPath("data.detail").type(JsonFieldType.STRING).description("상세 설명"),
					fieldWithPath("data.businessNumber").type(JsonFieldType.STRING).description("사업자 등록번호"),
					fieldWithPath("data.address").type(JsonFieldType.STRING).description("주소"),
					fieldWithPath("data.type").type(JsonFieldType.STRING).description("회사 타입 (CLIENT/DEV)"),
					fieldWithPath("data.contactPhoneNumber").type(JsonFieldType.STRING).description("연락처"),
					fieldWithPath("data.contactEmail").type(JsonFieldType.STRING).description("이메일"),
					fieldWithPath("data.logoImagePath").type(JsonFieldType.STRING).description("로고 이미지 경로").optional(),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("회사 목록 기본 조회 성공")
	@Sql("classpath:sql/company-list.sql")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 회사_목록_기본_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/company?page={page}&type={type}&keyword={keyword}&deleted={deleted}",
				1, "DEV", null, null)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("company-list-success01", companyListSuccessResource01()));
	}

	private ResourceSnippet companyListSuccessResource01() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Company API")
				.summary("회사 목록 조회 API")
				.description("회사 목록을 조회한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.queryParameters(
					parameterWithName("page").description("페이지 번호"),
					parameterWithName("type").description("회사 타입(DEV/CLIENT)"),
					parameterWithName("keyword").description("검색어").optional(),
					parameterWithName("deleted").description("삭제 여부").optional()
				)
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.companies.[].companyName").type(JsonFieldType.STRING).description("회사 이름"),
					fieldWithPath("data.companies.[].businessNumber").type(JsonFieldType.STRING).description("사업자 번호"),
					fieldWithPath("data.companies.[].address").type(JsonFieldType.STRING).description("사업자 주소"),
					fieldWithPath("data.companies.[].contactPhoneNumber").type(JsonFieldType.STRING)
						.description("대표 번호"),
					fieldWithPath("data.companies.[].deleted").type(JsonFieldType.BOOLEAN).description("삭제 여부"),
					fieldWithPath("data.companies.[].createdAt").type(JsonFieldType.STRING).description("생성 일자"),
					fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("총 갯수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}
}
