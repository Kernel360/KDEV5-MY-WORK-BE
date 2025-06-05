package kr.mywork.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mywork.base.listener.DataCleanUpExecutionListener;
import kr.mywork.domain.auth.service.JwtTokenProvider;
import kr.mywork.domain.member.model.MemberRole;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(value = {
	DataCleanUpExecutionListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@Sql("classpath:sql/member-test-users.sql")
abstract class RestDocsDocumentation {

	private static final String BEARER_PREFIX = "Bearer";

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected WebApplicationContext context;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@BeforeEach
	public void setUp(RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders
			.webAppContextSetup(this.context)
			.apply(springSecurity())
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.apply(documentationConfiguration(restDocumentation)
				.operationPreprocessors()
				.withRequestDefaults(modifyUris().removePort(), prettyPrint())
				.withResponseDefaults(modifyUris().removePort(), prettyPrint()))
			.alwaysDo(print())
			.build();
	}

	public String createUserAccessToken() {
		return this.jwtTokenProvider.createAccessToken(
			UUID.fromString("019739ed-f977-7c85-9138-7c8c0e2721d6"),
			"user01@example.com",
			MemberRole.USER.getRoleName());
	}

	public String createClientAdminAccessToken() {
		return this.jwtTokenProvider.createAccessToken(
			UUID.fromString("019739ec-b50a-7f17-b375-3740a1bffcf1"),
			"client_admin@example.com",
			MemberRole.CLIENT_ADMIN.getRoleName());
	}

	public String createDevAdminAccessToken() {
		return this.jwtTokenProvider.createAccessToken(
			UUID.fromString("019739ea-e7eb-76b7-b5e1-b9dc3ea1e9c2"),
			"dev_admin@example.com",
			MemberRole.DEV_ADMIN.getRoleName());
	}

	public String createSystemAccessToken() {
		return this.jwtTokenProvider.createAccessToken(
			UUID.fromString("0196f7a6-10b6-7123-a2dc-32c3861ea55e"),
			"system_admin@example.com",
			MemberRole.SYSTEM_ADMIN.getRoleName());
	}

	public String toBearerAuthorizationHeader(final String accessToken) {
		return String.format("%s %s", BEARER_PREFIX, accessToken);
	}
}
