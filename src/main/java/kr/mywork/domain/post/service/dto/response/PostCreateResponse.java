package kr.mywork.domain.post.service.dto.response;

import java.time.LocalDateTime;

import kr.mywork.domain.post.model.Post;

public record PostCreateResponse(LocalDateTime createdAt, String authorName, String content, String approval) {

	public static PostCreateResponse from(Post post) {
		return new PostCreateResponse(post.getCreatedAt(), post.getAuthorName(), post.getContent(), post.getApproval());

	}

};
