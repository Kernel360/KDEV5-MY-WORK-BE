package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.ReviewCreateResponse;

public record ReviewCreateWebResponse(UUID reviewId, UUID reviewParentId, String comment,
									  String authorName, String companyName) {
	public static ReviewCreateWebResponse fromServiceRequest(final ReviewCreateResponse reviewCreateResponse) {
		return new ReviewCreateWebResponse(
			reviewCreateResponse.reviewId(),
			reviewCreateResponse.reviewParentId(),
			reviewCreateResponse.comment(),
			reviewCreateResponse.authorName(),
			reviewCreateResponse.companyName());
	}
}
