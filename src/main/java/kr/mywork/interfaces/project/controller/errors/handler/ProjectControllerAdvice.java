package kr.mywork.interfaces.project.controller.errors.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project.errors.ProjectErrorType;
import kr.mywork.domain.project.errors.ProjectException;

@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class ProjectControllerAdvice {

	@ExceptionHandler(ProjectException.class)
	public ResponseEntity<ApiResponse<?>> handleProjectException(ProjectException exception) {
		final ProjectErrorType errorType = exception.getErrorType();

		return ResponseEntity.badRequest().body(
			ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage())
		);
	}

}
