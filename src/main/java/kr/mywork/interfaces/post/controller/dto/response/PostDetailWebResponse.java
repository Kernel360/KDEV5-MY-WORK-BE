package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.PostDetailResponse;

public record PostDetailWebResponse(UUID postId, String title, String content, String companyName, String authorName,
									String approval) {

	public static PostDetailWebResponse from(PostDetailResponse postDetailResponse) {
		return new PostDetailWebResponse(postDetailResponse.postId(), postDetailResponse.title(),
			postDetailResponse.content(), postDetailResponse.companyName(), postDetailResponse.authorName(),
			postDetailResponse.approval());
	}
}