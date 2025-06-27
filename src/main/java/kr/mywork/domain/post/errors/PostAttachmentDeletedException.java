package kr.mywork.domain.post.errors;

public class PostAttachmentDeletedException extends PostException {
	public PostAttachmentDeletedException(final PostErrorType errorType) {
		super(errorType);
	}
}
