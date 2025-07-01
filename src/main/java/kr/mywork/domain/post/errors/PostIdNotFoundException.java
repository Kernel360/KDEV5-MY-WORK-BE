package kr.mywork.domain.post.errors;

public class PostIdNotFoundException extends PostException {
    public PostIdNotFoundException(PostErrorType errorType) {
		super(errorType);
	}
}
