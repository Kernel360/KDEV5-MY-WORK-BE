package kr.mywork.domain.post.service.errors;

import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostException;

public class PostAttachmentAlreadyUploadException extends PostException {
	public PostAttachmentAlreadyUploadException(final PostErrorType errorType) {
		super(errorType);
	}
}
