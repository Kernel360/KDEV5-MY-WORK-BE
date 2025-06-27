package kr.mywork.common.api.support.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorType {
	ERROR_UNKNOWN(CommonErrorCode.ERROR_COMMON01, "알 수 없는 예외가 발생했습니다."),
	INVALID_REQUEST_ARGUMENT(CommonErrorCode.ERROR_COMMON02, "유효하지 않는 입력 값을 포함하고 있습니다."),
	UNAUTHORIZED(CommonErrorCode.ERROR_COMMON03, "인증이 필요하거나 로그인 정보가 올바르지 않습니다."),
	INVALID_CONTENT_TYPE(CommonErrorCode.ERROR_COMMON04, "요청의 Content-Type이 application/json이 아닙니다."),
	INVALID_INPUT_VALUE(CommonErrorCode.ERROR_COMMON05, "이메일 또는 비밀번호가 비어 있습니다."),
	INVALID_JSON_INPUT(CommonErrorCode.ERROR_COMMON06, "요청의 JSON 파싱에 실패했습니다."),
	HTTP_METHOD_NOT_SUPPORT(CommonErrorCode.ERROR_COMMON07, "지원하지 않는 HTTP 메서드 입니다."),
	HTTP_MESSAGE_NOT_READABLE(CommonErrorCode.ERROR_COMMON08, "요청 바디 중에 올바르지 않는 형태가 포함되어 있습니다. 요청 바디의 값을 확인해주세요."),
	NO_RESOURCE_FOUND(CommonErrorCode.ERROR_COMMON09, "요청하신 리소스를 찾을 수 없습니다.");

	private final CommonErrorCode errorCode;
	private final String message;
}
