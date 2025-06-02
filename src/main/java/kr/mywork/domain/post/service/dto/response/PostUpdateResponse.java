package kr.mywork.domain.post.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.post.model.Post;

public record PostUpdateResponse(UUID id, UUID projectStepId, String title, String companyName, String authorName,
								 String content, String approval, LocalDateTime createdAt) {

	public static PostUpdateResponse from(Post post) {
		return new PostUpdateResponse(
			post.getId(), post.getProjectStepId(), post.getTitle(),
			post.getCompanyName(), post.getAuthorName(), post.getContent(),
			post.getApproval(), post.getCreatedAt()
		);

	}
};
