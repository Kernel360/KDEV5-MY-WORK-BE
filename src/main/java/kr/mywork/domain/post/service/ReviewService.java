package kr.mywork.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.post.model.Review;
import kr.mywork.domain.post.service.dto.request.ReviewCreateRequest;
import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;
import kr.mywork.domain.post.repository.ReviewRepository;
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
}
