package com.jbank.olb.common.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
// @RestController
public class CustomizedResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		String code = String.valueOf(HttpStatus.NOT_FOUND.value());
		
		ErrorDetail errorDetail = new ErrorDetail(code, ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public final ResponseEntity<ErrorDetail> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
		String code = String.valueOf(HttpStatus.UNAUTHORIZED.value());
		
		ErrorDetail errorDetail = new ErrorDetail(code, ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ForbiddenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public final ResponseEntity<ErrorDetail> handleUnauthorizedException(ForbiddenException ex, WebRequest request) {
		String code = String.valueOf(HttpStatus.FORBIDDEN.value());
		
		ErrorDetail errorDetail = new ErrorDetail(code, ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<ErrorDetail> handleUnauthorizedException(MethodArgumentNotValidException ex, WebRequest request) {
		String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
		String message = ex.getBindingResult().toString();
		
		ErrorDetail errorDetail = new ErrorDetail(code, message, request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<ErrorDetail> handleUnauthorizedException(ConstraintViolationException ex, WebRequest request) {
		String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
		String message = ex.getMessage();
		
		ErrorDetail errorDetail = new ErrorDetail(code, message, request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	 
}

