package kr.mywork.domain.post.service.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostUpdateRequest {

	private final UUID id;

	private final String title;

	private final String content;

}
