package kr.mywork.domain.post.uploader;

import java.net.URL;
import java.util.UUID;

public interface PostAttachmentFileHandler {
	URL createUploadUrl(UUID postId, String fileName);
}
