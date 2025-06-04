package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.ReviewModifyResponse;

public record ReviewModifyWebResponse(UUID reviewId, String comment) {
	public static ReviewModifyWebResponse fromServiceRequest(final ReviewModifyResponse reviewModifyResponse) {
		return new ReviewModifyWebResponse(reviewModifyResponse.reviewId(), reviewModifyResponse.comment());
	}
}
