package kr.mywork.docs;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.infrastructure.member.rdb.JpaMemberRepository;
import kr.mywork.interfaces.member.controller.dto.request.MemberCreateWebRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;

public class MemberDocumentationTest extends RestDocsDocumentation{

    @Autowired
    private JpaMemberRepository jpaMemberRepository;

    @Test
    @DisplayName("회원 생성 테스트")
    void 회원_생성_테스트() throws Exception{
        // given
        final MemberCreateWebRequest memberCreateWebRequest = new MemberCreateWebRequest(
                "홍길동","개발팀","주임","010-1234-5678","honggildon@naver.com","19990909","DEV"
        );

        final String requestBody = objectMapper.writeValueAsString(memberCreateWebRequest);

        // when
        final ResultActions result = mockMvc.perform(
                post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        // then
        result.andExpectAll(
                status().isOk(),
                jsonPath("$.result").value(ResultType.SUCCESS.name()),
                jsonPath("$.data").exists(),
                jsonPath("$.error").doesNotExist())
        .andDo(document("member-create-success", memberCreateSuccessResourse()));

    }

    private ResourceSnippet memberCreateSuccessResourse() {
        return resource(
                ResourceSnippetParameters.builder()
                    .tag("Member API")
                    .summary("회원 생성 API")
                    .description("전달받은 정보를 통해 회원을 생성한다.")
                    .requestHeaders(
                            headerWithName(HttpHeaders.CONTENT_TYPE).description("컨텐츠 타입"))
                    .responseFields(
                            fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
                            fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("회원 아이디"),
                            fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보"))
                    .build()
        );
    }



}
