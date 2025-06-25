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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.interfaces.project_checklist.controller.dto.request.ProjectCheckListApprovalWebRequest;
import kr.mywork.interfaces.project_checklist.controller.dto.request.ProjectCheckListCreateWebRequest;
import kr.mywork.interfaces.project_checklist.controller.dto.request.ProjectCheckListUpdateWebRequest;

public class ProjectCheckListDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("체크리스트 생성 성공")
	@Sql("classpath:sql/project-check-list-for-create.sql")
	void 체크리스트_생성_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();
		final UUID clientCompanyId = UUID.fromString("1234a9a9-90b6-9898-a9dc-92c9861aa98c"); // UUID ver7
		final UUID devCompanyId = UUID.fromString("01973f6c-4b84-70e3-be24-1daf26e5808a"); // UUID ver7
		final UUID projectStepId = UUID.fromString("01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01"); // UUID ver7

		final ProjectCheckListCreateWebRequest projectCheckListCreateWebRequest = new ProjectCheckListCreateWebRequest(
			"체크리스트 제목", "체크리스트 내용", devCompanyId, "대기");

		final String requestBody = objectMapper.writeValueAsString(projectCheckListCreateWebRequest);

		//when
		final ResultActions result = mockMvc.perform(
			post("/api/projects/check-lists")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-check-list-create-success",
				ProjectCheckListCreateSuccessResource()));
	}

	private ResourceSnippet ProjectCheckListCreateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("ProjectCheckList API")
				.summary("프로젝트 체크 리스트 생성 API")
				.description("프로젝트 세부 단계에 대한 체크 리스트를 생성한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.title").type(JsonFieldType.STRING).description("체크리스트 제목"),
					fieldWithPath("data.content").type(JsonFieldType.STRING).description("체크리스트 내용"),
					fieldWithPath("data.projectStepId").type(JsonFieldType.STRING).description("프로젝트 단계 id"),
					fieldWithPath("data.approval").type(JsonFieldType.STRING).description("승인여부"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("체크리스트 상세 조회 성공")
	@Sql("classpath:sql/project-check-list-for-get.sql")
	void 체크리스트_상세_조회_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();
		final UUID checkListId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // UUID ver7
		//when
		final ResultActions result = mockMvc.perform(
			get("/api/projects/check-lists/{checkListId}", checkListId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-check-list-get-success",
				ProjectCheckListGetSuccessResource()));
	}

	private ResourceSnippet ProjectCheckListGetSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("ProjectCheckList API")
				.summary("프로젝트 체크 리스트 상세 조회 API")
				.description("프로젝트 상세 조회를 한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("체크리스트 ID"),
					fieldWithPath("data.title").type(JsonFieldType.STRING).description("체크리스트 제목"),
					fieldWithPath("data.content").type(JsonFieldType.STRING).description("체크리스트 내용"),
					fieldWithPath("data.companyName").type(JsonFieldType.STRING).description("작성자 회사명"),
					fieldWithPath("data.authorName").type(JsonFieldType.STRING).description("작성자 명"),
					fieldWithPath("data.approval").type(JsonFieldType.STRING).description("승인여부"),
					fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성일"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("체크리스트 수정 성공")
	@Sql("classpath:sql/project-check-list-for-update.sql")
	void 체크리스트_수정_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();
		final UUID checkListId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // UUID ver7

		final ProjectCheckListUpdateWebRequest projectCheckListUpdateWebRequest = new ProjectCheckListUpdateWebRequest(
			checkListId, "체크리스트 제목", "체크리스트 내용");

		final String requestBody = objectMapper.writeValueAsString(projectCheckListUpdateWebRequest);

		//when
		final ResultActions result = mockMvc.perform(
			put("/api/projects/check-lists")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-check-list-update-success",
				ProjectCheckListUpdateSuccessResource()));
	}

	private ResourceSnippet ProjectCheckListUpdateSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("ProjectCheckList API")
				.summary("프로젝트 체크 리스트 수정 API")
				.description("프로젝트 체크 리스트를 수정한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("체크리스트 id"),
					fieldWithPath("data.title").type(JsonFieldType.STRING).description("체크리스트 제목"),
					fieldWithPath("data.content").type(JsonFieldType.STRING).description("체크리스트 내용"),
					fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
					fieldWithPath("data.approval").type(JsonFieldType.STRING).description("승인여부"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("체크리스트 삭제 성공")
	@Sql("classpath:sql/project-check-list-for-delete.sql")
	void 체크리스트_삭제_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();
		final UUID checkListId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // UUID ver7

		//when
		final ResultActions result = mockMvc.perform(
			delete("/api/projects/check-lists/{checkListId}", checkListId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-check-list-delete-success",
				ProjectCheckListDeleteSuccessResource()));
	}

	private ResourceSnippet ProjectCheckListDeleteSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("ProjectCheckList API")
				.summary("프로젝트 체크 리스트 삭제 API")
				.description("프로젝트 체크 리스트를 soft 삭제한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.checkListId").type(JsonFieldType.STRING).description("체크리스트 id"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("체크리스트 승인_반려_성공")
	@Sql("classpath:sql/project-check-list-for-approval.sql")
	void 체크리스트_승인_반려_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();
		final UUID checkListId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // UUID ver7

		final ProjectCheckListApprovalWebRequest projectCheckListApprovalWebRequest =
			new ProjectCheckListApprovalWebRequest(checkListId, "APPROVED", "승인한 내용");

		final String requestBody = objectMapper.writeValueAsString(projectCheckListApprovalWebRequest);

		//when
		final ResultActions result = mockMvc.perform(
			put("/api/projects/check-lists/{checklistId}/approval", checkListId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
				.content(requestBody));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-check-list-approval-success",
				ProjectCheckListApprovalSuccessResource()));
	}

	private ResourceSnippet ProjectCheckListApprovalSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("ProjectCheckList API")
				.summary("프로젝트 체크 승인,반려 수정 API")
				.description("프로젝트 체크 리스트를 수정요청,승인,반려한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("체크리스트 id"),
					fieldWithPath("data.title").type(JsonFieldType.STRING).description("체크리스트 제목"),
					fieldWithPath("data.content").type(JsonFieldType.STRING).description("체크리스트 내용"),
					fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
					fieldWithPath("data.approval").type(JsonFieldType.STRING).description("승인여부"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("프로젝트 체크리스트 진행률 조회_성공")
	@Sql("classpath:sql/project_checklist_progress.sql")
	void 프로젝트_체크리스트_진행률_조회_성공() throws Exception {
		//given
		final String accessToken = createSystemAccessToken();
		final UUID projectId = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"); // UUID ver7

		//when
		final ResultActions result = mockMvc.perform(
			get("/api/projects/{projectId}/check-list/progress", projectId)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken)));

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-check-list-progress-success",
				projectCheckListProgressSuccessResource()));
	}

	private ResourceSnippet projectCheckListProgressSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("ProjectCheckList API")
				.summary("프로젝트 체크리스트 진행률 조회 API")
				.description("프로젝트의 각 단계별 체크리스트 진행률을 조회한다.")
				.pathParameters(
					parameterWithName("projectId").description("프로젝트 ID"))
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
					fieldWithPath("data.projectCheckListProgress[].projectStepId").type(JsonFieldType.STRING)
						.description("프로젝트 단계 ID"),
					fieldWithPath("data.projectCheckListProgress[].projectStepName").type(JsonFieldType.STRING)
						.description("프로젝트 단계명"),
					fieldWithPath("data.projectCheckListProgress[].totalCount").type(JsonFieldType.NUMBER)
						.description("전체 체크리스트 개수"),
					fieldWithPath("data.projectCheckListProgress[].approvalCount").type(JsonFieldType.NUMBER)
						.description("승인된 체크리스트 개수"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}

	@Test
	@DisplayName("프로젝트별 체크리스트 목록 조회 성공")
	@Sql("classpath:sql/project_checklist_by_step02.sql")
	void 프로젝트별_체크리스트_목록_조회_성공() throws Exception {
		// given
		final String accessToken = createSystemAccessToken();
		final UUID projectId = UUID.fromString("01975d7f-052c-7d8f-819b-9a08f322ead3");
		final UUID projectStatusId = UUID.fromString("01975dbf-6fbe-71b9-8dab-0455e155bbe8"); // Optional

		// when
		final ResultActions result = mockMvc.perform(
			get("/api/projects/{projectId}/check-list?projectStepId={projectStepId}", projectId,
				projectStatusId.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, toBearerAuthorizationHeader(accessToken))
		);

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data.projectCheckLists").isArray(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("project-check-list-list-success", projectCheckListListSuccessResource()));
	}

	private ResourceSnippet projectCheckListListSuccessResource() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("ProjectCheckList API")
				.summary("프로젝트 체크리스트 목록 조회 API")
				.description("프로젝트 ID와 상태 ID로 체크리스트 목록을 조회한다.")
				.pathParameters(
					parameterWithName("projectId").description("프로젝트 단계 ID"))
				.queryParameters(
					parameterWithName("projectStepId").description("프로젝트 단계 ID").optional())
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"),
					headerWithName(HttpHeaders.AUTHORIZATION).description("엑세스 토큰"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.projectCheckLists[].id").type(JsonFieldType.STRING)
						.description("체크리스트 ID"),
					fieldWithPath("data.projectCheckLists[].authorName").type(JsonFieldType.STRING)
						.description("작성자 명"),
					fieldWithPath("data.projectCheckLists[].title").type(JsonFieldType.STRING)
						.description("체크리스트 명"),
					fieldWithPath("data.projectCheckLists[].content").type(JsonFieldType.STRING)
						.description("체크리스트 내용"),
					fieldWithPath("data.projectCheckLists[].approval").type(JsonFieldType.STRING).description("승인 여부"),
					fieldWithPath("data.projectCheckLists[].projectStepName").type(JsonFieldType.STRING)
						.description("프로젝트 단계명"),
					fieldWithPath("data.projectCheckLists[].createdAt").type(JsonFieldType.STRING)
						.description("체크리스트 생성 일시"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build());
	}
}
