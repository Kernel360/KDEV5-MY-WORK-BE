package kr.mywork.domain.post.service.errors;

import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostException;

public class PostAttachmentNotFoundException extends PostException {
	public PostAttachmentNotFoundException(final PostErrorType errorType) {
		super(errorType);
	}
}
