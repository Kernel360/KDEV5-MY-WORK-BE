package kr.mywork.domain.post.service.errors;

import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostException;

public class PostAttachmentInactiveException extends PostException {
	public PostAttachmentInactiveException(final PostErrorType errorType) {
		super(errorType);
	}
}
