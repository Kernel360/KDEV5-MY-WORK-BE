package kr.mywork.interfaces.post.controller.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class PostAttachmentUploadUrlReissueWebRequest {
	private final UUID postAttachmentId;
	private final String fileName;
}
