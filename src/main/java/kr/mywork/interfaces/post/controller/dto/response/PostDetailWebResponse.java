package kr.mywork.interfaces.post.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.post.service.dto.response.PostDetailResponse;

public record PostDetailWebResponse(UUID postId, String title, String content, String companyName, String authorName,
									String approval, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt) {

	public static PostDetailWebResponse from(PostDetailResponse postDetailResponse) {
		return new PostDetailWebResponse(postDetailResponse.postId(), postDetailResponse.title(),
			postDetailResponse.content(), postDetailResponse.companyName(), postDetailResponse.authorName(),
			postDetailResponse.approval(), postDetailResponse.createdAt());
	}
}