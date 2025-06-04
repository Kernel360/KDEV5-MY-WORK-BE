package kr.mywork.domain.post.repository;

import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.post.model.Review;

public interface ReviewRepository {
	Review save(Review review);
	Optional<Review> findById(UUID reviewId);
}
