package kr.mywork.interfaces.post.controller.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class PostAttachmentActiveWebRequest {

	private final UUID postId;

	@Size(max = 3, message = "{post-attachments-max-size}")
	private final List<UUID> postAttachmentIds;

	private final Boolean active;
}
