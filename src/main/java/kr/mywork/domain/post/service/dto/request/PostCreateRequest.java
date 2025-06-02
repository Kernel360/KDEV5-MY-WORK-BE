package kr.mywork.domain.post.service.dto.request;

import java.util.UUID;

import kr.mywork.domain.post.model.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateRequest {

	private final UUID id;

	private final UUID projectStepId;

	private final String title;

	private final String companyName;

	private final String authorName;

	private final String content;

	private final String approval;

	private final Boolean deleted;

	public Post toEntity() {
		return new Post(this.getId(), this.getProjectStepId(), this.getTitle(),
			this.getCompanyName(), this.getAuthorName(), this.getContent(),
			this.getApproval(), this.getDeleted());
	}
}
