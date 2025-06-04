package kr.mywork.interfaces.post.controller.errors.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.errors.review.ReviewErrorType;
import kr.mywork.domain.post.errors.review.ReviewException;

@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class ReviewControllerAdvice {

	@ExceptionHandler(ReviewException.class)
	public ResponseEntity<ApiResponse<?>> handlePostException(ReviewException exception) {
		final ReviewErrorType errorType = exception.getErrorType();

		return ResponseEntity.badRequest().body(
			ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}
}
