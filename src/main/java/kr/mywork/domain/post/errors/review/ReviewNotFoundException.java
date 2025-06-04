package kr.mywork.domain.post.errors.review;

public class ReviewNotFoundException extends ReviewException {
	public ReviewNotFoundException(final ReviewErrorType errorType) {
		super(errorType);
	}
}
