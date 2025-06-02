package kr.mywork.interfaces.post.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mywork.common.api.support.response.ResultType;
import kr.mywork.domain.post.service.ReviewService;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.interfaces.post.controller.dto.request.ReviewCreateWebRequest;

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
	void 리뷰_생성_요청_성공() throws Exception {
		// given
		final UUID postId = UUID.fromString("01972f9b-232a-7dbe-aad2-3bffc0b78ced");

		when(reviewService.save(any())).thenReturn(new ReviewCreateResponse(postId, null,
			UUID.fromString("01972ea5-73ff-75e1-9083-d1d51a0f186a"), "코멘트01", "작성자1", "회사01"));

		final ReviewCreateWebRequest reviewCreateWebRequest = new ReviewCreateWebRequest(
			postId, "코멘트01", null);

		final String requestBody = objectMapper.writeValueAsString(reviewCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/posts/reviews")
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
	void 리뷰_생성_요청_입력_값_실패() throws Exception {
		// given
		final UUID postId = UUID.fromString("01972f9b-232a-7dbe-aad2-3bffc0b78ced");

		when(reviewService.save(any())).thenReturn(new ReviewCreateResponse(postId, null,
			UUID.fromString("01972ea5-73ff-75e1-9083-d1d51a0f186a"), "코멘트01", "작성자1", "회사01"));

		final ReviewCreateWebRequest reviewCreateWebRequest = new ReviewCreateWebRequest(
			postId, "", null);

		final String requestBody = objectMapper.writeValueAsString(reviewCreateWebRequest);

		// when
		final ResultActions result = mockMvc.perform(post("/api/posts/reviews")
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

}
