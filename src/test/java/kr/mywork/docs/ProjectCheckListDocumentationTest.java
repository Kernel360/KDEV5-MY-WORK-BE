package kr.mywork.docs;

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

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
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
			"체크리스트 제목", devCompanyId, clientCompanyId, projectStepId, "대기");

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
			.andDo(MockMvcRestDocumentationWrapper.document("project-check-list-create-success",
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
					fieldWithPath("data.devCompanyId").type(JsonFieldType.STRING).description("개발사 id"),
					fieldWithPath("data.clientCompanyId").type(JsonFieldType.STRING).description("고객사 id"),
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
			.andDo(MockMvcRestDocumentationWrapper.document("project-check-list-get-success",
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
					fieldWithPath("data.title").type(JsonFieldType.STRING).description("체크리스트 제목"),
					fieldWithPath("data.approval").type(JsonFieldType.STRING).description("승인여부"),
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
			checkListId, "체크리스트 제목");

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
			.andDo(MockMvcRestDocumentationWrapper.document("project-check-list-update-success",
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
			.andDo(MockMvcRestDocumentationWrapper.document("project-check-list-delete-success",
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

		final ProjectCheckListApprovalWebRequest projectCheckListApprovalWebRequest = new ProjectCheckListApprovalWebRequest(
			checkListId, "승인");

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
			.andDo(MockMvcRestDocumentationWrapper.document("project-check-list-approval-success",
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
					fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
					fieldWithPath("data.approval").type(JsonFieldType.STRING).description("승인여부"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}


}
