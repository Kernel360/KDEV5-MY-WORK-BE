package kr.mywork.interfaces.post.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.post.service.dto.response.PostSelectResponse;

public record PostSelectWebResponse(UUID postId, String authorName,
									String title,
									@JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime createdAt,
									String approval,
									String projectStepTitle) {

	public static PostSelectWebResponse from(PostSelectResponse postSelectResponse) {
		return new PostSelectWebResponse(
			postSelectResponse.getPostId(),
			postSelectResponse.getAuthorName(),
			postSelectResponse.getTitle(),
			postSelectResponse.getCreateAt(),
			postSelectResponse.getApproval(),
			postSelectResponse.getProjectStepTitle()
		);
	}
}
