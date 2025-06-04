package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.model.Post;

public record PostDetailResponse(UUID postId, String title, String content, String companyName, String authorName,
								 String approval) {

	public static PostDetailResponse from(Post post) {
		return new PostDetailResponse(post.getId(), post.getTitle(), post.getContent(), post.getCompanyName(),
			post.getAuthorName(), post.getApproval());

	}

};
