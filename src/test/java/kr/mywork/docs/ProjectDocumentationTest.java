package kr.mywork.docs;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.project.controller.dto.request.ProjectCreateWebRequest;
import kr.mywork.interfaces.project.controller.dto.request.ProjectDeleteWebRequest;
import kr.mywork.interfaces.project.controller.dto.request.ProjectUpdateWebRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("프로젝트 할당될 직원 ")
	@Sql("classpath:sql/project-for-member-list.sql")
	void 프로젝트_할당_멤버_조회_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();

		final UUID projectId = UUID.fromString("d73b1f10-47e2-7a2d-c1e5-f17125d62999");
		final UUID companyId = UUID.fromString("a62a0c20-91e2-7c2d-b0e5-e16115c61888");

		//when
		final ResultActions result = mockMvc.perform(
			get("/api/projects/members")
				.param("projectId", projectId.toString())
				.param("companyId", companyId.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
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
				.tag("Project API")
				.summary("프로젝트 할당할 멤버 조회 API")
				.description("프로젝트 멤버를 조회한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.queryParameters(
					parameterWithName("projectId").description("프로젝트 아이디"),
					parameterWithName("companyId").description("회사 아이디"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.members[].memberId").type(JsonFieldType.STRING)
						.description("프로젝트 할당 가능한 멤버 아이디"),
					fieldWithPath("data.members[].memberName").type(JsonFieldType.STRING)
						.description("프로젝트 할당 가능한 멤버 이름"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("프로젝트 단건 상세 조회 성공")
	@Sql("classpath:sql/project-details.sql")
	void 프로젝트_단건_상세_조회_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();

		final UUID projectId = UUID.fromString("01973a42-0995-74aa-9298-a25cb8dae6ef");

		//when
		final ResultActions result = mockMvc.perform(
			get("/api/projects/{projectId}", projectId.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-detail-success", projectDetailSuccessResource()));
	}

	private ResourceSnippet projectDetailSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project API")
				.summary("프로젝트 단건 상세 조회 API")
				.description("프로젝트 상세 내용을 조회한다")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.projectId").type(JsonFieldType.STRING).description("프로젝트 아이디"),
					fieldWithPath("data.name").type(JsonFieldType.STRING).description("프로젝트 이름"),
					fieldWithPath("data.startAt").type(JsonFieldType.STRING).description("프로젝트 시작일"),
					fieldWithPath("data.endAt").type(JsonFieldType.STRING).description("프로젝트 종료일"),
					fieldWithPath("data.step").type(JsonFieldType.STRING).description("프로젝트 진행 상태"),
					fieldWithPath("data.detail").type(JsonFieldType.STRING).description("프로젝트 상세 설명"),
					fieldWithPath("data.deleted").type(JsonFieldType.BOOLEAN).description("프로젝트 삭제 여부"),
					fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("프로젝트 생성 일자"),
					fieldWithPath("data.projectAmount").type(JsonFieldType.NUMBER).description("프로젝트 결제 가격"),
					fieldWithPath("data.devCompanyId").type(JsonFieldType.STRING).description("프로젝트 개발사 아이디"),
					fieldWithPath("data.devCompanyName").type(JsonFieldType.STRING).description("프로젝트 개발사 이름"),
					fieldWithPath("data.devContactPhoneNum").type(JsonFieldType.STRING).description("프로젝트 개발사 전화번호"),
					fieldWithPath("data.clientCompanyId").type(JsonFieldType.STRING).description("프로젝트 고객사 아이디"),
					fieldWithPath("data.clientCompanyName").type(JsonFieldType.STRING).description("프로젝트 고객사 이름"),
					fieldWithPath("data.clientContactPhoneNum").type(JsonFieldType.STRING).description("프로젝트 고객사 전화번호"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("프로젝트 생성 성공")
	void 프로젝트_생성_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		final ProjectCreateWebRequest request = new ProjectCreateWebRequest(
			"고객사 개발 프로젝트",
			LocalDateTime.of(2025, 1, 1, 10, 0),
			LocalDateTime.of(2025, 12, 31, 18, 0),
			"IN_PROGRESS",
			"서비스 개발 프로젝트입니다.",
			UUID.fromString("019759dd-378a-7590-9bd4-b204a064a120"),
			UUID.fromString("019759de-4cdf-70e6-a0c9-3188cac11476"),
			200L
		);

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/projects")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(objectMapper.writeValueAsString(request)));

		// then
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value("SUCCESS"))
			.andExpect(jsonPath("$.data").exists())
			.andExpect(jsonPath("$.error").doesNotExist())
			.andDo(document("project-create-success", projectCreateSuccessResource()));
	}

	private ResourceSnippet projectCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project API")
				.summary("프로젝트 생성 API")
				.description("새로운 프로젝트를 등록하고 개발사, 고객사를 할당한다.")
				.requestFields(
					fieldWithPath("name").type(JsonFieldType.STRING).description("프로젝트 이름"),
					fieldWithPath("startAt").type(JsonFieldType.STRING).description("시작 날짜"),
					fieldWithPath("endAt").type(JsonFieldType.STRING).description("종료 날짜"),
					fieldWithPath("step").type(JsonFieldType.STRING)
						.description("프로젝트 단계 (CONTRACT, IN_PROGRESS, PAYMENT, COMPLETED)"),
					fieldWithPath("detail").type(JsonFieldType.STRING).description("프로젝트 상세 설명"),
					fieldWithPath("devCompanyId").type(JsonFieldType.STRING).description("개발사 UUID"),
					fieldWithPath("clientCompanyId").type(JsonFieldType.STRING).description("클라이언트 UUID"),
					fieldWithPath("projectAmount").type(JsonFieldType.NUMBER).description("프로젝트 결제 가격"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("생성된 프로젝트 ID"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("프로젝트 수정 성공")
	@Sql("classpath:sql/project-update.sql")
	void 프로젝트_수정_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		final UUID projectId = UUID.fromString("01975a03-765d-7760-b82f-1bc8ba1b2ab6");

		final ProjectUpdateWebRequest request = new ProjectUpdateWebRequest(
			"고객사 프로젝트 이름 수정",
			LocalDateTime.of(2025, 2, 1, 9, 0),
			LocalDateTime.of(2025, 11, 30, 18, 0),
			"COMPLETED",
			"프로젝트가 완료되었습니다.",
			false,
			100L);

		// when
		final ResultActions result = mockMvc.perform(
			put("/api/projects/{projectId}", projectId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(objectMapper.writeValueAsString(request)));

		// then
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value("SUCCESS"))
			.andExpect(jsonPath("$.data").exists())
			.andDo(document("project-update-success", projectUpdateSuccessResource()));
	}

	private ResourceSnippet projectUpdateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project API")
				.summary("프로젝트 수정 API")
				.description("기존 프로젝트 정보를 수정한다.")
				.pathParameters(
					parameterWithName("projectId").description("수정할 프로젝트 ID"))
				.requestFields(
					fieldWithPath("name").type(JsonFieldType.STRING).description("프로젝트 이름"),
					fieldWithPath("startAt").type(JsonFieldType.STRING).description("시작 날짜"),
					fieldWithPath("endAt").type(JsonFieldType.STRING).description("종료 날짜"),
					fieldWithPath("step").type(JsonFieldType.STRING)
						.description("프로젝트 단계 (CONTRACT, IN_PROGRESS, PAYMENT, COMPLETED)"),
					fieldWithPath("detail").type(JsonFieldType.STRING).description("프로젝트 상세 설명"),
					fieldWithPath("deleted").type(JsonFieldType.BOOLEAN).description("삭제 여부"),
					fieldWithPath("projectAmount").type(JsonFieldType.NUMBER).description("프로젝트 결제 가격"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("프로젝트 ID"),
					fieldWithPath("data.name").type(JsonFieldType.STRING).description("프로젝트 이름"),
					fieldWithPath("data.startAt").type(JsonFieldType.STRING).description("프로젝트 시작일"),
					fieldWithPath("data.endAt").type(JsonFieldType.STRING).description("프로젝트 종료일"),
					fieldWithPath("data.step").type(JsonFieldType.STRING).description("진행 상태"),
					fieldWithPath("data.detail").type(JsonFieldType.STRING).description("상세 설명"),
					fieldWithPath("data.deleted").type(JsonFieldType.BOOLEAN).description("삭제 여부"),
					fieldWithPath("data.projectAmount").type(JsonFieldType.NUMBER).description("프로젝트 결제 가격"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("프로젝트 삭제 성공")
	@Sql("classpath:sql/project-delete.sql")
	void 프로젝트_삭제_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();

		final UUID projectId = UUID.fromString("01975a03-765d-7760-b82f-1bc8ba1b2ab6");

		final ProjectDeleteWebRequest request = new ProjectDeleteWebRequest(projectId);

		// when
		final ResultActions result = mockMvc.perform(
			delete("/api/projects")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(objectMapper.writeValueAsString(request))
		);

		// then
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value("SUCCESS"))
			.andExpect(jsonPath("$.data").exists())
			.andDo(document("project-delete-success", projectDeleteSuccessResource()));
	}

	private ResourceSnippet projectDeleteSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project API")
				.summary("프로젝트 삭제 API")
				.description("지정한 프로젝트를 삭제한다.")
				.requestFields(
					fieldWithPath("id").type(JsonFieldType.STRING).description("삭제할 프로젝트 ID"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("삭제된 프로젝트 ID"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}

	@Test
	@DisplayName("프로젝트 목록 조회 성공")
	@Sql("classpath:sql/project-keyword-search.sql")
	void 프로젝트_목록_조회_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();

		//when
		final ResultActions result = mockMvc.perform(
			get("/api/projects?page={page}&keywordType={keywordType}&keyword={keyword}&step={step}",
				1, "DEV_COMPANY_NAME", "개발사", "IN_PROGRESS")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.data.projects").isArray(),
				jsonPath("$.data.totalCount").isNumber(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-list-success", projectListSuccessResource()));
	}

	private ResourceSnippet projectListSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Project API")
				.summary("프로젝트 목록 조회 API")
				.description("프로젝트 목록을 조회한다 (검색 조건, 페이징 포함)")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.queryParameters(
					parameterWithName("page").description("페이지 번호 (1부터 시작)"),
					parameterWithName("keywordType").description(
						"검색 키워드 타입 (DEV_COMPANY_NAME|CLIENT_COMPANY_NAME|PROJECT_NAME)").optional(),
					parameterWithName("keyword").description("검색 키워드").optional(),
					parameterWithName("step").description("프로젝트 진행 상태").optional())
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.projects").type(JsonFieldType.ARRAY).description("프로젝트 목록"),
					fieldWithPath("data.projects[].id").type(JsonFieldType.STRING).description("프로젝트 아이디"),
					fieldWithPath("data.projects[].name").type(JsonFieldType.STRING).description("프로젝트 이름"),
					fieldWithPath("data.projects[].startAt").type(JsonFieldType.STRING).description("프로젝트 시작일"),
					fieldWithPath("data.projects[].endAt").type(JsonFieldType.STRING).description("프로젝트 종료일"),
					fieldWithPath("data.projects[].step").type(JsonFieldType.STRING).description("프로젝트 진행 상태"),
					fieldWithPath("data.projects[].devCompanyId").type(JsonFieldType.STRING).description("개발사 아이디"),
					fieldWithPath("data.projects[].devCompanyName").type(JsonFieldType.STRING).description("개발사 이름"),
					fieldWithPath("data.projects[].clientCompanyId").type(JsonFieldType.STRING).description("고객사 아이디"),
					fieldWithPath("data.projects[].clientCompanyName").type(JsonFieldType.STRING).description("고객사 이름"),
					fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("전체 프로젝트 개수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
	@Test
	@DisplayName("내 프로젝트만 조회")
	@Sql("classpath:sql/my-project-list.sql")
	void 내_프로젝트_조회_성공() throws Exception {
		//given
		final String accessToken = createDevAdminAccessToken();

		//when
		final ResultActions result = mockMvc.perform(
				get("/api/projects/my-projects")
						.contentType(MediaType.APPLICATION_JSON)
						.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
						status().isOk(),
						jsonPath("$.result").value(ResultType.SUCCESS.name()),
						jsonPath("$.data").exists(),
						jsonPath("$.error").doesNotExist())
				.andDo(document("my-project-list-success", myProjectListSuccessResource()));
	}

	private ResourceSnippet myProjectListSuccessResource() {
		return resource(
				ResourceSnippetParameters.builder()
						.tag("Project API")
						.summary("내 프로젝트 리스트 조회 API")
						.description("내 프로젝트 리스트를 조회한다")
						.requestHeaders(
								headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
								headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
						.responseFields(
								fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
								fieldWithPath("data.projects[].id").type(JsonFieldType.STRING).description("프로젝트 id"),
								fieldWithPath("data.projects[].name").type(JsonFieldType.STRING).description("프로젝트 이름"),
								fieldWithPath("data.projects[].detail").type(JsonFieldType.STRING).description("프로젝트 상세"),
								fieldWithPath("data.projects[].startAt").type(JsonFieldType.STRING).description("프로젝트 시작일"),
								fieldWithPath("data.projects[].endAt").type(JsonFieldType.STRING).description("프로젝트 마감일"),
								fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
						.build());
	}
}
