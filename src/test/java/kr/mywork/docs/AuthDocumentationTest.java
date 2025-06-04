package kr.mywork.docs;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.cookies.CookieDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.jayway.jsonpath.JsonPath;

import jakarta.servlet.http.Cookie;
import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.domain.auth.service.JwtTokenProvider;

public class AuthDocumentationTest extends RestDocsDocumentation{

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Test
	@DisplayName("로그인 성공")
	@Sql("classpath:sql/member-auth.sql")
	void 로그인_성공() throws Exception {
		// given
		final String email = "admin@example.com";
		final String password = "1234";

		final String requestBody = objectMapper.writeValueAsString(Map.of(
			"email", email,
			"password", password
		));

		// when
		final ResultActions result = mockMvc.perform(
			post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
		);

		// then
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name()))
			.andExpect(jsonPath("$.data.accessToken").exists())
			.andExpect(jsonPath("$.data.expiresAt").exists())
			.andExpect(cookie().exists("refreshToken"))
			.andDo(document("auth-login-success",
				resource(
					ResourceSnippetParameters.builder()
						.tag("Auth API")
						.summary("로그인 API")
						.description("이메일과 비밀번호로 로그인한다. 성공 시 액세스 토큰과 리프레시 토큰이 발급된다.")
						.requestFields(
							fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
							fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
						)
						.responseFields(
							fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
							fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("JWT 액세스 토큰"),
							fieldWithPath("data.expiresAt").type(JsonFieldType.STRING).description("액세스 토큰 만료 시각"),
							fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보")
						)
						.build()
				)
			));
	}

	@Test
	@DisplayName("로그아웃 성공")
	@Sql("classpath:sql/member-auth.sql")
	void 로그아웃_성공() throws Exception {
		// 1. 로그인해서 accessToken & refreshToken 획득
		final String loginBody = objectMapper.writeValueAsString(Map.of(
			"email", "admin@example.com",
			"password", "1234"
		));

		final MvcResult loginResult = mockMvc.perform(post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(loginBody))
			.andExpect(status().isOk())
			.andReturn();

		String responseJson = loginResult.getResponse().getContentAsString();
		String accessToken = JsonPath.read(responseJson, "$.data.accessToken");

		Cookie refreshTokenCookie = loginResult.getResponse().getCookie("refreshToken");

		// 2. 로그아웃 요청 + 문서화
		mockMvc.perform(post("/api/logout")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
				.cookie(refreshTokenCookie))
			.andExpect(status().isOk())
			.andDo(document("auth-logout",
				requestHeaders(
					headerWithName("Authorization").description("액세스 토큰 (Bearer)").getAttributes()
				),
				requestCookies(
					cookieWithName("refreshToken").description("리프레시 토큰")
				),
				responseFields(
					fieldWithPath("result").description("SUCCESS 또는 FAILURE"),
					fieldWithPath("data").description("응답 데이터 (로그아웃은 null)").optional(),
					fieldWithPath("error").description("에러 메시지 (없으면 null)").optional()
				)
			));
	}



}
