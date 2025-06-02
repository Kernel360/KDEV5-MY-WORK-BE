package kr.mywork.interfaces.post.controller.errors.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostException;

@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class PostControllerAdvice {

	@ExceptionHandler(PostException.class)
	public ResponseEntity<ApiResponse<?>> handlePostException(PostException exception) {
		final PostErrorType errorType = exception.getErrorType();

		return ResponseEntity.badRequest().body(
			ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}
}
