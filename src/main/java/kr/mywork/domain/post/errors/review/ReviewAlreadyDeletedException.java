package kr.mywork.domain.post.errors.review;

public class ReviewAlreadyDeletedException extends ReviewException {
	public ReviewAlreadyDeletedException(final ReviewErrorType errorType) {
		super(errorType);
	}
}
