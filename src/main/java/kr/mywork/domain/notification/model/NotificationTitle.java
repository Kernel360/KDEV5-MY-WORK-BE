package kr.mywork.domain.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationTitle {
	POST_APPROVE("게시글 승인 응답"),
	PROJECT_CHECK_LIST_APPROVED("체크리스트 승인 응답"),
	PROJECT_CHECK_LIST_REJECTED("체크리스트 거절 응답"),
	PROJECT_CHECK_LIST_REQUEST_CHANGES("체크리스트 수정 요청 응답");

	private final String title;
}
