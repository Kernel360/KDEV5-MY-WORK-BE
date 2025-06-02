package kr.mywork.domain.post.errors;

import lombok.Getter;

@Getter
public abstract class PostException extends RuntimeException {
	private final PostErrorType errorType;

	public PostException(final PostErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
