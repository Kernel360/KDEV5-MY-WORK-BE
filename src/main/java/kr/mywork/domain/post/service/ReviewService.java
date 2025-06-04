package kr.mywork.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.post.errors.review.ReviewErrorType;
import kr.mywork.domain.post.errors.review.ReviewNotFoundException;
import kr.mywork.domain.post.model.Review;
import kr.mywork.domain.post.repository.ReviewRepository;
import kr.mywork.domain.post.service.dto.request.ReviewCreateRequest;
import kr.mywork.domain.post.service.dto.request.ReviewDeleteRequest;
import kr.mywork.domain.post.service.dto.request.ReviewModifyRequest;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.domain.post.service.dto.response.ReviewDeleteResponse;
import kr.mywork.domain.post.service.dto.response.ReviewModifyResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

	private final ReviewRepository reviewRepository;

	public ReviewCreateResponse save(final ReviewCreateRequest reviewCreateRequest) {
		// TODO Post 조회 및 검증 로직 추가 필요

		final Review review = reviewCreateRequest.toEntity();
		final Review savedReview = reviewRepository.save(review);

		return ReviewCreateResponse.fromEntity(savedReview);
	}

	public ReviewModifyResponse modifyComment(final ReviewModifyRequest reviewModifyRequest) {
		// TODO Project 유효성 검증 코드 추가 필요
		// TODO 본인 수정 내용 추가

		final Review review = reviewRepository.findById(reviewModifyRequest.reviewId())
			.orElseThrow(() -> new ReviewNotFoundException(ReviewErrorType.REVIEW_NOT_FOUND));

		review.modifyComment(reviewModifyRequest.comment());

		return new ReviewModifyResponse(review.getId(), review.getComment());
	}

	public ReviewDeleteResponse deleteReview(final ReviewDeleteRequest reviewDeleteRequest) {
		// TODO 본인 작성 검증 내용 추가
		final Review review = reviewRepository.findById(reviewDeleteRequest.reviewId())
			.orElseThrow(() -> new ReviewNotFoundException(ReviewErrorType.REVIEW_NOT_FOUND));

		review.delete();

		return new ReviewDeleteResponse(review.getId());
	}
}
