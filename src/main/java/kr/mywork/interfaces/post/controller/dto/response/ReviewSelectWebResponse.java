package kr.mywork.interfaces.post.controller.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.mywork.domain.post.service.dto.ReviewSelectResponse;

public record ReviewSelectWebResponse(UUID reviewId, String authorName, String comment, String companyName,
									  @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime createdAt,
									  @JsonProperty("childReviews") List<ReviewSelectWebResponse> childReviewSelectResponses) {

	public static ReviewSelectWebResponse fromServiceResponse(final ReviewSelectResponse reviewSelectResponse) {
		return new ReviewSelectWebResponse(
			reviewSelectResponse.getReviewId(),
			reviewSelectResponse.getAuthorName(),
			reviewSelectResponse.getComment(),
			reviewSelectResponse.getCompanyName(),
			reviewSelectResponse.getCreatedAt(),
			initChildReviewWebService(reviewSelectResponse)
		);
	}

	private static List<ReviewSelectWebResponse> initChildReviewWebService(
		final ReviewSelectResponse reviewSelectResponse) {

		if (reviewSelectResponse.getChildReviewSelectResponses() == null) {
			return null;
		}

		return reviewSelectResponse.getChildReviewSelectResponses()
			.stream()
			.map(reviewSelectResponse01 -> new ReviewSelectWebResponse(
				reviewSelectResponse01.getReviewId(),
				reviewSelectResponse01.getAuthorName(),
				reviewSelectResponse01.getComment(),
				reviewSelectResponse01.getCompanyName(),
				reviewSelectResponse01.getCreatedAt(),
				null
			)).toList();
	}
}
