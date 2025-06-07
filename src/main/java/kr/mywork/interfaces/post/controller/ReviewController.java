package kr.mywork.interfaces.post.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.service.ReviewService;
import kr.mywork.domain.post.service.dto.ReviewSelectResponse;
import kr.mywork.domain.post.service.dto.request.ReviewCreateRequest;
import kr.mywork.domain.post.service.dto.request.ReviewDeleteRequest;
import kr.mywork.domain.post.service.dto.request.ReviewModifyRequest;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.domain.post.service.dto.response.ReviewDeleteResponse;
import kr.mywork.domain.post.service.dto.response.ReviewModifyResponse;
import kr.mywork.interfaces.post.controller.dto.request.ReviewCreateWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.ReviewModifyWebRequest;
import kr.mywork.interfaces.post.controller.dto.response.ReviewCreateWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.ReviewDeleteWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.ReviewModifyWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.ReviewSelectWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.ReviewsSelectWebResponse;
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

	@DeleteMapping("/api/reviews/{reviewId}")
	public ApiResponse<ReviewDeleteWebResponse> deleteReview(
		// TODO 사용자 인증 정보 첨부하기
		@PathVariable("reviewId") UUID reviewId) {

		final ReviewDeleteResponse reviewDeleteResponse = reviewService.deleteReview(new ReviewDeleteRequest(
			UUID.fromString("019738c6-a0e8-7cbf-9b39-fb91ac12af33"), // TODO 사용자 아이디 추가
			reviewId
		));

		return ApiResponse.success(new ReviewDeleteWebResponse(reviewDeleteResponse.reviewId()));
	}

	@GetMapping("/api/posts/{postId}/reviews")
	public ApiResponse<ReviewsSelectWebResponse> findAllReviewByPaging(
		@PathVariable("postId") UUID postId,
		@RequestParam("page") @Min(value = 1, message = "{invalid.page-size}") Integer page) {

		final List<ReviewSelectResponse> reviewSelectResponses = reviewService.findAllReviewsWithPaging(postId, page);
		final List<ReviewSelectWebResponse> reviewSelectWebResponses = reviewSelectResponses.stream()
			.map(ReviewSelectWebResponse::fromServiceResponse)
			.toList();

		return ApiResponse.success(new ReviewsSelectWebResponse(reviewSelectWebResponses));
	}
}
