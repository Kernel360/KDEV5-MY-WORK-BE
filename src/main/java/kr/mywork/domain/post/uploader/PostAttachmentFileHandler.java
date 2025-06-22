package kr.mywork.domain.post.uploader;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

public interface PostAttachmentFileHandler {
	URL createUploadUrl(UUID postId, String fileName);

	URL issueDownloadUrl(String key, Duration duration);
}
