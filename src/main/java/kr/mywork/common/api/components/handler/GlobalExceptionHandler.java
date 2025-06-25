package kr.mywork.common.api.components.handler;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.mywork.common.api.support.error.CommonErrorType;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.auth.errors.AuthErrorType;
import kr.mywork.domain.auth.errors.AuthException;
import kr.mywork.interfaces.auth.exception.CommonAuthenticationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse<?>> handleException(Exception exception) {
		final CommonErrorType errorType = CommonErrorType.ERROR_UNKNOWN;

		log.error("exception : {}, message : {}\n stackTraces: {}",
			exception.getClass().getSimpleName(),
			exception.getMessage(),
			String.join("\n", getStackTraces(exception)));

		return ResponseEntity.internalServerError()
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException exception) {
		final CommonErrorType errorType = CommonErrorType.ERROR_UNKNOWN;

		logWarn(exception);

		return ResponseEntity.internalServerError()
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}

	@ExceptionHandler(CommonAuthenticationException.class)
	public ResponseEntity<ApiResponse<?>> handleCommonAuthenticationException(CommonAuthenticationException exception) {
		final CommonErrorType errorType = exception.getErrorType();

		log.warn("authentication error: {}, message: {}\n stackTrace: {}",
			exception.getClass().getSimpleName(),
			errorType.getMessage(),
			String.join("\n", getStackTraces(exception)));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse<?>> handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException exception) {

		final CommonErrorType errorType = CommonErrorType.HTTP_METHOD_NOT_SUPPORT;

		logInfo(exception);

		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {

		final CommonErrorType errorType = CommonErrorType.HTTP_MESSAGE_NOT_READABLE;

		logInfo(exception);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ApiResponse<?>> handleAuthException(AuthException exception) {
		final AuthErrorType errorType = exception.getErrorType();

		log.warn("auth error: {}, message: {}\n stackTrace: {}",
			exception.getClass().getSimpleName(),
			errorType.getMessage(),
			String.join("\n", getStackTraces(exception)));

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}

	private void logInfo(final Exception exception) {
		log.info("exception : {}, message : {}",
			exception.getClass().getSimpleName(),
			exception.getMessage());
	}

	private void logWarn(final Exception exception) {
		log.warn("exception : {}, message : {}\n stackTraces: {}",
			exception.getClass().getSimpleName(),
			exception.getMessage(),
			String.join("\n", getStackTraces(exception)));
	}

	private List<String> getStackTraces(final Exception exception) {
		return Arrays.stream(exception.getStackTrace())
			.map(StackTraceElement::toString)
			.toList();
	}
}
