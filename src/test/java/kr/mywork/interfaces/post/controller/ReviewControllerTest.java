package kr.mywork.interfaces.post.controller;

import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.domain.post.service.ReviewService;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.domain.post.service.dto.response.ReviewModifyResponse;
import kr.mywork.interfaces.post.controller.dto.request.ReviewCreateWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.ReviewModifyWebRequest;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private ReviewService reviewService;

	@Test
	@DisplayName("리뷰 생성 요청 성공")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 리뷰_생성_요청_성공() throws Exception {
		// given
		final UUID postId = UUID.fromString("01972f9b-232a-7dbe-aad2-3bffc0b78ced");
		final UUID reviewId = UUID.fromString("01972ea5-73ff-75e1-9083-d1d51a0f186a");

		when(reviewService.save(any())).thenReturn(new ReviewCreateResponse(reviewId, null, "코멘트01", "작성자1", "회사01"));

		final ReviewCreateWebRequest reviewCreateWebRequest = new ReviewCreateWebRequest(
			postId, "코멘트01", null);

		final String requestBody = objectMapper.writeValueAsString(reviewCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/reviews")
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(print());
	}

	@Test
	@DisplayName("리뷰 생성 요청 입력 값 실패 (빈 코멘트)")
	@WithMockUser(roles = "SYSTEM_ADMIN")
	void 리뷰_생성_요청_입력_값_실패() throws Exception {
		// given
		final UUID postId = UUID.fromString("01972f9b-232a-7dbe-aad2-3bffc0b78ced");
		final UUID reviewId = UUID.fromString("01972ea5-73ff-75e1-9083-d1d51a0f186a");

		when(reviewService.save(any())).thenReturn(new ReviewCreateResponse(reviewId, null, "코멘트01", "작성자1", "회사01"));

		final ReviewCreateWebRequest reviewCreateWebRequest = new ReviewCreateWebRequest(
			postId, "", null);

		final String requestBody = objectMapper.writeValueAsString(reviewCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/reviews")
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));

		// then
		result.andExpectAll(
				status().is4xxClientError(),
				jsonPath("$.result").value(ResultType.ERROR.name()),
				jsonPath("$.data").doesNotExist(),
				jsonPath("$.error").exists())
			.andDo(print());
	}

	@Test
	@DisplayName("리뷰 수정 요청 성공")
	void 리뷰_수정_요청_성공() throws Exception {
		// given
		final UUID memberId = UUID.fromString("01973844-b287-73d0-8f9d-f86fad4ac4c3");
		final UUID reviewId = UUID.fromString("01973844-d55f-7350-b505-f198eaf0cd38");

		when(reviewService.modifyComment(any())).thenReturn(new ReviewModifyResponse(reviewId, "코멘트01_수정"));

		final ReviewModifyWebRequest reviewModifyWebRequest = new ReviewModifyWebRequest("코멘트01_수정");

		final String requestBody = objectMapper.writeValueAsString(reviewModifyWebRequest);

		// when
		final ResultActions result = mockMvc.perform(put("/api/reviews/{reviewId}", reviewId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));

		// then
		result.andExpectAll(
				status().isOk(),
				jsonPath("$.result").value(ResultType.SUCCESS.name()),
				jsonPath("$.data").exists(),
				jsonPath("$.error").doesNotExist())
			.andDo(print());
	}

	@ParameterizedTest
	@MethodSource("reviewModifyInvalidCommentSource")
	@DisplayName("리뷰 수정 요청 입력 값 실패")
	void 리뷰_수정_요청_입력_값_실패(String comment) throws Exception {
		// given
		final UUID memberId = UUID.fromString("01973844-b287-73d0-8f9d-f86fad4ac4c3");
		final UUID reviewId = UUID.fromString("01973844-d55f-7350-b505-f198eaf0cd38");
		final String emptyComment = "  ";

		when(reviewService.modifyComment(any())).thenReturn(new ReviewModifyResponse(reviewId, "코멘트01_수정"));

		final ReviewModifyWebRequest reviewModifyWebRequest = new ReviewModifyWebRequest(emptyComment);

		final String requestBody = objectMapper.writeValueAsString(reviewModifyWebRequest);

		// when
		final ResultActions result = mockMvc.perform(put("/api/reviews/{reviewId}", reviewId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));

		// then
		result.andExpectAll(
				status().is4xxClientError(),
				jsonPath("$.result").value(ResultType.ERROR.name()),
				jsonPath("$.data").doesNotExist(),
				jsonPath("$.error").exists())
			.andDo(print());
	}

	private static Stream<Arguments> reviewModifyInvalidCommentSource() {
		return Stream.of(
			arguments(""), // 빈 값
			arguments("   "), // 띄어쓰기 값
			arguments(
				"111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111")
			// 길이 200 초과 값
		);
	}
}
