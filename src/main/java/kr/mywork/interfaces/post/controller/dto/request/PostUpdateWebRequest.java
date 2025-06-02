package kr.mywork.interfaces.post.controller.dto.request;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.request.PostUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostUpdateWebRequest {

	private final UUID id;

	private final String title;

	private final String content;

	public PostUpdateRequest toServiceDto() {
		return new PostUpdateRequest(this.id, this.title, this.content);
	}

}
