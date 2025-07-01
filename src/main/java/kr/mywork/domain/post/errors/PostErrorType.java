package kr.mywork.domain.post.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostErrorType {
	ID_NOT_FOUND(PostErrorCode.ERROR_POST01, "게시글 아이디를 찾을 수 없습니다."),
	POST_NOT_FOUND(PostErrorCode.ERROR_POST02, "게시글을 찾을 수 없습니다."),
	ATTACHMENT_NOT_FOUND(PostErrorCode.ERROR_POST03, "첨부 파일을 찾을 수 없습니다."),
	ATTACHMENT_ALREADY_UPLOADED(PostErrorCode.ERROR_POST04, "첨부파일이 이미 업로드 되었습니다."),
	ATTACHMENT_INACTIVE(PostErrorCode.ERROR_POST05, "첨부파일이 활성화되지 않았습니다."),
	ATTACHMENT_DELETED(PostErrorCode.ERROR_POST06, "첨부파일이 이미 삭제 되었습니다."),
	ATTACHMENT_MAX(PostErrorCode.ERROR_POST07, "첨부파일 최대 업로드 수를 초과했습니다."),
	ATTACHMENT_ALREADY_UPLOADED_NAME(PostErrorCode.ERROR_POST08, "업로드 된 첨부 파일 이름이 존재합니다."),
	POST_DELETED(PostErrorCode.ERROR_POST09, "삭제된 게시글 입니다.");

	private final PostErrorCode errorCode;
	private final String message;
}
