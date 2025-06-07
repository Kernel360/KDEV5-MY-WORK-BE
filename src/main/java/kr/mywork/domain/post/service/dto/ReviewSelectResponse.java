package kr.mywork.domain.post.service.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ReviewSelectResponse {
	private UUID reviewId;
	private String authorName;
	private String comment;
	private String companyName;
	private UUID parentId;

	@Setter
	private List<ReviewSelectResponse> childReviewSelectResponses;

	private LocalDateTime createdAt;
}
