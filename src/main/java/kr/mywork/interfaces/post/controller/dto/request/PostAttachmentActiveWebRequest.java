package kr.mywork.interfaces.post.controller.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class PostAttachmentActiveWebRequest {
	private final UUID postId;
	private final Boolean active;
}
