package kr.mywork.interfaces.company.controller.errors.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyException;

@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class CompanyControllerAdvice {

	@ExceptionHandler(CompanyException.class)
	public ResponseEntity<ApiResponse<?>> handleCompanyException(CompanyException exception) {
		final CompanyErrorType errorType = exception.getErrorType();
		return ResponseEntity.badRequest()
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}
}
