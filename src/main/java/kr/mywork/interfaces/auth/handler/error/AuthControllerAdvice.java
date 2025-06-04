package kr.mywork.interfaces.auth.handler.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.auth.errors.AuthErrorType;
import kr.mywork.domain.auth.errors.AuthException;

@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class AuthControllerAdvice {

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ApiResponse<?>> handleAuthException(AuthException exception) {
		final AuthErrorType errorType = exception.getErrorType();

		HttpStatus status = (errorType == AuthErrorType.ACCESS_DENIED)
			? HttpStatus.FORBIDDEN
			: HttpStatus.UNAUTHORIZED;

		return ResponseEntity.status(status)
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}
}
