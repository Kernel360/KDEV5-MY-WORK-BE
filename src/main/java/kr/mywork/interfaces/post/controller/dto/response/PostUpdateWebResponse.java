package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.PostUpdateResponse;

public record PostUpdateWebResponse(UUID postId, String title, String content, String companyName, String authorName,
									String approval) {

	public static PostUpdateWebResponse from(PostUpdateResponse postUpdateResponse) {
		return new PostUpdateWebResponse(postUpdateResponse.id(), postUpdateResponse.title(),
			postUpdateResponse.content(), postUpdateResponse.companyName(), postUpdateResponse.authorName(),
			postUpdateResponse.approval());
	}
}
