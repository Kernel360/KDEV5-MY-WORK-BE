package kr.mywork.interfaces.project_checklist.controller.errors.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project_checklist.errors.ProjectCheckListErrorType;
import kr.mywork.domain.project_checklist.errors.ProjectCheckListException;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class CheckListControllerAdvice {

	@ExceptionHandler(ProjectCheckListException.class)
	public ResponseEntity<ApiResponse<?>> handlePostException(ProjectCheckListException exception) {
		final ProjectCheckListErrorType errorType = exception.getErrorType();

		return ResponseEntity.badRequest().body(
			ApiResponse.error(errorType.getErrorCode().name(), errorType.getMessage()));
	}
}
