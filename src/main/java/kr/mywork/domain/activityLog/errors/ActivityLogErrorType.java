package kr.mywork.domain.activityLog.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActivityLogErrorType {
	ACTION_TYPE_NOT_FOUND(ActivityLogErrorCode.ERROR_ACTIVITY_LOG_TYPE01, "액션 대상이 유효하지 않습니다."),
	TARGET_LOG_NOT_FOUND(ActivityLogErrorCode.ERROR_ACTIVITY_LOG_TYPE02, "로그 대상이 유효하지 않습니다."),
	FIELD_TYPE_NOT_FOUND(ActivityLogErrorCode.ERROR_ACTIVITY_LOG_TYPE03, "필드 타입이 유효하지 않습니다.");

	private final ActivityLogErrorCode errorCode;
	private final String message;
}
