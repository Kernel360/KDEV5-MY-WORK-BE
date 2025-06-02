package kr.mywork.domain.post.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostErrorType {
	ID_NOT_FOUND(PostErrorCode.ERROR_POST01, "게시글 아이디를 찾을 수 없습니다."),
	POST_NOT_FOUND(PostErrorCode.ERROR_POST02, "게시글을 찾을 수 없습니다.");

	private final PostErrorCode errorCode;
	private final String message;
}
