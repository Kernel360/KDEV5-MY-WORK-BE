package kr.mywork.interfaces.post.controller;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.service.ReviewService;
import kr.mywork.domain.post.service.dto.request.ReviewCreateRequest;
import kr.mywork.domain.post.service.dto.request.ReviewModifyRequest;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.domain.post.service.dto.response.ReviewModifyResponse;
import kr.mywork.interfaces.post.controller.dto.request.ReviewCreateWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.ReviewModifyWebRequest;
import kr.mywork.interfaces.post.controller.dto.response.ReviewCreateWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.ReviewModifyWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping("/api/reviews")
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

	@PutMapping("/api/reviews/{reviewId}")
	public ApiResponse<ReviewModifyWebResponse> createReview(
		// TODO 인증 사용자 정보 필요
		@PathVariable(name = "reviewId") UUID reviewId,
		@RequestBody @Valid ReviewModifyWebRequest reviewModifyWebRequest) {

		final ReviewModifyRequest reviewModifyRequest = new ReviewModifyRequest(
			UUID.fromString("01972ea5-73ff-75e1-9083-d1d51a0f186a"), // TODO 인증 로직 추가시 해당 코드 변경
			reviewId, reviewModifyWebRequest.getComment());

		final ReviewModifyResponse reviewModifyResponse = reviewService.modifyComment(reviewModifyRequest);

		return ApiResponse.success(ReviewModifyWebResponse.fromServiceRequest(reviewModifyResponse));
	}
}
