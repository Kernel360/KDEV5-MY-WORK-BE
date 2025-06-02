package kr.mywork.interfaces.post.controller.dto.request;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateWebRequest {

	private final UUID id;

	private final UUID projectStepId;

	private final String title;

	private final String companyName;

	private final String authorName;

	private final String content;

	public PostCreateRequest toServiceDto() {
		return new PostCreateRequest(this.id, this.projectStepId, this.title, this.companyName,
			this.authorName, this.content, "PENDING", false);
	}

}
