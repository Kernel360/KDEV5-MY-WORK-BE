package kr.mywork.domain.post.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.post.model.Review;

public interface JpaReviewRepository extends JpaRepository<Review, UUID> {
}
