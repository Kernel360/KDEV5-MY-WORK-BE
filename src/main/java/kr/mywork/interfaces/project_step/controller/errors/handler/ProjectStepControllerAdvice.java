package kr.mywork.interfaces.project_step.controller.errors.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project_step.errors.ProjectStepErrorType;
import kr.mywork.domain.project_step.errors.ProjectStepException;

@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class ProjectStepControllerAdvice {

	@ExceptionHandler(ProjectStepException.class)
	public ResponseEntity<ApiResponse<?>> handleProjectStepException(ProjectStepException exception) {
		final ProjectStepErrorType errorType = exception.getErrorType();
		return ResponseEntity.badRequest()
			.body(ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}
}
