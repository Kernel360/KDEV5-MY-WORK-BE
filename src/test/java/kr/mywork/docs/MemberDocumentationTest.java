package kr.mywork.docs;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
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

public class MemberDocumentationTest extends RestDocsDocumentation {

	@Test
	@DisplayName("회사 직원 목록 조회 테스트 성공")
	@Sql("classpath:sql/company-member-get.sql")
	 void 회사직원_조회_테스트_성공() throws Exception{
		//given
		final UUID id = UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e");

		//when
		final ResultActions result = mockMvc.perform(
			get("/api/member/company/{companyId}",id,0)
				.contentType(MediaType.APPLICATION_JSON)
		);

		//then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(document("companyMember-get-success", CompanyMemberGetSuccess()));



	}
	private ResourceSnippet CompanyMemberGetSuccess() {
		return resource(
			ResourceSnippetParameters.builder()
				.tag("Member API")
				.summary("회사 직원 조회 API")
				.description("회사의 직원 목록을 조회한다.")
				.requestHeaders(
					headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
				.responseFields(
					fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
					fieldWithPath("data.total").type(JsonFieldType.NUMBER).description("전체 멤버 수"),
					fieldWithPath("data.members[].id").type(JsonFieldType.STRING).description("멤버 고유 식별자 (UUID)"),
					fieldWithPath("data.members[].name").type(JsonFieldType.STRING).description("멤버 이름"),
					fieldWithPath("data.members[].phoneNumber").type(JsonFieldType.STRING).description("멤버 전화번호"),
					fieldWithPath("data.members[].position").type(JsonFieldType.STRING).description("멤버 직급"),
					fieldWithPath("data.members[].department").type(JsonFieldType.STRING).description("멤버 부서"),
					fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
				.build()
		);
	}
}
