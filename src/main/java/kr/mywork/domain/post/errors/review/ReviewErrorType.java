package kr.mywork.domain.post.errors.review;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorType {

	REVIEW_NOT_FOUND(ReviewErrorCode.ERROR_REVIEW01, "리뷰를 찾을 수 없습니다."),
	REVIEW_ALREADY_DELETED(ReviewErrorCode.ERROR_REVIEW02, "이미 삭제된 리뷰입니다.");

	private final ReviewErrorCode errorCode;
	private final String message;
}
