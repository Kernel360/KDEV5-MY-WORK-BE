package kr.mywork.domain.post.errors;

public class PostNotFoundException extends PostException {
	public PostNotFoundException(PostErrorType errorType) {
		super(errorType);
	}
}
