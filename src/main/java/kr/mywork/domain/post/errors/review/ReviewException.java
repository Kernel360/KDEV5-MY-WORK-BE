package kr.mywork.domain.post.errors.review;

import lombok.Getter;

@Getter
public abstract class ReviewException extends RuntimeException{
	private final ReviewErrorType errorType;

	public ReviewException(final ReviewErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
