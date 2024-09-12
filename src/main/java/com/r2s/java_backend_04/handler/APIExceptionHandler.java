package com.r2s.java_backend_04.handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.r2s.java_backend_04.exception.UserNotFoundException;
import com.r2s.java_backend_04.response.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class APIExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { UserNotFoundException.class })
	public ErrorResponse handle(final UserNotFoundException exception) {
		return ErrorResponse.of("NotFound.user", "User not found with id: " + exception.getUserId());
	}
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handle(final ConstraintViolationException exception) {
		Map<String, Object> errors = exception
				.getConstraintViolations()
				.stream()
				.collect(Collectors.toMap(x -> x.getPropertyPath().toString(), x -> x.getMessage()));
		return ErrorResponse.of("InternalError", null, errors);
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handle(final MethodArgumentNotValidException exception) {
		List<String> errors = exception
				.getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.toList();
		return ErrorResponse.of("InternalError", null, Map.of("Errors", errors));
	}
}
