package kr.mywork.domain.post.service.dto.request;

import java.util.UUID;

import kr.mywork.domain.post.model.Review;

public record ReviewCreateRequest(UUID postId, UUID parentId, UUID memberId, String comment,
								  String authorName, String companyName) {

	public Review toEntity() {
		return new Review(postId, parentId, memberId, comment, companyName, authorName);
	}
}
