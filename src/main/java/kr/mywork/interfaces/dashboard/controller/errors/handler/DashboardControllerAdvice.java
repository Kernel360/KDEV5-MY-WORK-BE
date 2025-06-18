package kr.mywork.interfaces.dashboard.controller.errors.handler;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.dashboard.service.errors.DashboardErrorType;
import kr.mywork.domain.dashboard.service.errors.DashboardException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class DashboardControllerAdvice {

	@ExceptionHandler(DashboardException.class)
	public ResponseEntity<ApiResponse<?>> handlePostException(DashboardException exception) {
		final DashboardErrorType errorType = exception.getErrorType();

		return ResponseEntity.badRequest().body(
			ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}
}
