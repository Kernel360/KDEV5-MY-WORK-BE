package kr.mywork.interfaces.post.controller.dto.request;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import kr.mywork.domain.post.service.dto.request.PostUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostUpdateWebRequest {

	private final String title;

	private final String content;

	public PostUpdateRequest toServiceDto(UUID id) {
		return new PostUpdateRequest(id, this.title, this.content);
	}

}
