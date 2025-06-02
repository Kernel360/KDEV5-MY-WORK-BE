package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.model.Review;

public record ReviewCreateResponse(UUID postId, UUID parentId, UUID memberId, String comment,
								   String authorName, String companyName) {

	public static ReviewCreateResponse fromEntity(final Review review) {
		return new ReviewCreateResponse(review.getPostId(), review.getParentId(), review.getMemberId(),
			review.getComment(), review.getAuthorName(), review.getCompanyName());
	}
}
