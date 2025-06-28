package kr.mywork.domain.post.errors;

public class MaxPostAttachmentsException extends PostException {
	public MaxPostAttachmentsException(final PostErrorType errorType) {
		super(errorType);
	}
}
