package kr.mywork.interfaces.post.controller;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.service.ReviewService;
import kr.mywork.domain.post.service.dto.request.ReviewCreateRequest;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.interfaces.post.controller.dto.request.ReviewCreateWebRequest;
import kr.mywork.interfaces.post.controller.dto.response.ReviewCreateWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping("/api/posts/reviews")
	public ApiResponse<ReviewCreateWebResponse> createReview(
		//TODO 인증 객체 전달 로직 필요
		@RequestBody @Valid ReviewCreateWebRequest reviewCreateWebRequest) {

		final ReviewCreateRequest reviewCreateRequest = new ReviewCreateRequest(
			reviewCreateWebRequest.getPostId(),
			reviewCreateWebRequest.getParentId(),
			UUID.fromString("01972ea5-73ff-75e1-9083-d1d51a0f186a"), // TODO 인증 로직 추가시 해당 코드 변경
			reviewCreateWebRequest.getComment(),
			"작성자1", // TODO 인증 로직 추가시 해당 코드 변경
			"회사01"); // TODO 인증 로직 추가시 해당 코드 변경

		final ReviewCreateResponse reviewCreateResponse = reviewService.save(reviewCreateRequest);
		return ApiResponse.success(ReviewCreateWebResponse.fromServiceRequest(reviewCreateResponse));
	}
}
