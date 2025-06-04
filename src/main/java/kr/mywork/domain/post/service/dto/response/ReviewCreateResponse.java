package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.model.Review;

public record ReviewCreateResponse(UUID reviewId, UUID reviewParentId, String comment,
								   String authorName, String companyName) {

	public static ReviewCreateResponse fromEntity(final Review review) {
		return new ReviewCreateResponse(review.getId(), review.getParentId(),
			review.getComment(), review.getAuthorName(), review.getCompanyName());
	}
}
