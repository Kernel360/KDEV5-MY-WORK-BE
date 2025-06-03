package kr.mywork.interfaces.post.controller.dto.request;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import kr.mywork.domain.post.service.dto.request.PostUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostUpdateWebRequest {

	@NotNull
	@Length(min = 1, max = 200, message = "{post-invalid-length-title}")
	private final String title;

	@NotNull
	@Length(min = 1, max = 500, message = "{post-invalid-length-content}")
	private final String content;

	public PostUpdateRequest toServiceDto(final UUID postId) {
		return new PostUpdateRequest(postId, this.title, this.content);
	}

}
