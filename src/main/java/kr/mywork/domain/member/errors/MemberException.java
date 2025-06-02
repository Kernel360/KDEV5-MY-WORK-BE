package kr.mywork.domain.member.errors;

import lombok.Getter;

@Getter
public abstract class MemberException extends RuntimeException {
	private final MemberErrorType errorType;

	public MemberException(final MemberErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
