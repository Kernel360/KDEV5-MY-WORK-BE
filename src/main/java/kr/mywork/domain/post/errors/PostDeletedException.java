package kr.mywork.domain.post.errors;

public class PostDeletedException extends PostException {
	public PostDeletedException(final PostErrorType errorType) {
		super(errorType);
	}
}
