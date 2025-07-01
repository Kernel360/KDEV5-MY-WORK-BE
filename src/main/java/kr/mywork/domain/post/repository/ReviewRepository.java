package kr.mywork.domain.post.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.post.model.Review;
import kr.mywork.domain.post.service.dto.ReviewSelectResponse;

public interface ReviewRepository {
	Review save(Review review);
	Optional<Review> findById(UUID reviewId);

	List<ReviewSelectResponse> findParentReviewsWithPaging(UUID postId, Integer pageNumber, int pageSize);

	List<ReviewSelectResponse> findByIds(UUID postId, List<UUID> parentIds);

	Long deletePostReviews(UUID postId);
}
