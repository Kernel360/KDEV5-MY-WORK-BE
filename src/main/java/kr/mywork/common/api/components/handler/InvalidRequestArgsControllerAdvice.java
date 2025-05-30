package kr.mywork.common.api.components.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import kr.mywork.common.api.support.error.CommonErrorCode;
import kr.mywork.common.api.support.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class InvalidRequestArgsControllerAdvice {

	private static final String ARGUMENT_EXCEPTION_DELIMITER = "/";
	private static final String FIELD_REGEX = "\\.";

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponse<?>> handleConstraintViolationException(
		MethodArgumentNotValidException methodArgumentNotValidException) {

		final CommonErrorCode errorCode = CommonErrorCode.ERROR_COMMON02;
		final List<String> errorMessages = new ArrayList<>();

		final BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			final String errorMessage = String.format("%s (value: %s)", fieldError.getDefaultMessage(), fieldError.getRejectedValue());
			errorMessages.add(errorMessage);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ApiResponse.error(errorCode.name(), String.join(ARGUMENT_EXCEPTION_DELIMITER, errorMessages), null));
	}

	@ExceptionHandler(value = ConstraintViolationException .class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponse<?>> handleConstraintViolationException(
		ConstraintViolationException constraintViolationException) {

		final CommonErrorCode errorCode = CommonErrorCode.ERROR_COMMON02;
		final List<String> errorMessages = new ArrayList<>();

		final Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			final String errorMessage = String.format("%s : %s(value: %s)",
				constraintViolation.getPropertyPath().toString().split(FIELD_REGEX)[1],
				constraintViolation.getMessage(),
				constraintViolation.getInvalidValue());

			errorMessages.add(errorMessage);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ApiResponse.error(errorCode.name(), String.join(ARGUMENT_EXCEPTION_DELIMITER, errorMessages), null));
	}
}
