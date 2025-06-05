package kr.mywork.interfaces.post.controller.dto.request;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateWebRequest {

	@NotNull
	private final UUID id;

	@NotNull
	private final UUID projectStepId;

	@Length(min = 1, max = 200, message = "{post-invalid-length-title}")
	private final String title;

	@NotNull
	@Length(min = 1, max = 30, message = "{post-invalid-length-companyName}")
	private final String companyName;

	@NotNull
	@Length(min = 1, max = 30, message = "{post-invalid-length-authorName}")
	private final String authorName;

	@Length(min = 1, max = 500, message = "{post-invalid-length-content}")
	private final String content;

	public PostCreateRequest toServiceDto() {
		return new PostCreateRequest(this.id, this.projectStepId, this.title, this.companyName,
			this.authorName, this.content, "PENDING", false);
	}

}
