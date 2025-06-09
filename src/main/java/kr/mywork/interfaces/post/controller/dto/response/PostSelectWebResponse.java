package kr.mywork.interfaces.post.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.post.service.dto.response.PostSelectResponse;

public record PostSelectWebResponse(UUID postId, String authorName,
									String title,
									@JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime createdAt,
									String approval,
									String projectStepName) {

	public static PostSelectWebResponse from(PostSelectResponse postSelectResponse) {
		return new PostSelectWebResponse(
			postSelectResponse.postId(),
			postSelectResponse.authorName(),
			postSelectResponse.title(),
			postSelectResponse.createAt(),
			postSelectResponse.approval(),
			postSelectResponse.projectStepName()
		);
	}
}
