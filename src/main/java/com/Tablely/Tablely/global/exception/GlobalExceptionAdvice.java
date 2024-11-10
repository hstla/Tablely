package com.Tablely.Tablely.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException e) {
		return createErrorResponse(e, e.getErrorCode());
	}

	private ResponseEntity<ErrorResponse> createErrorResponse(Exception e, ErrorCode errorCode) {
		log.info(e.getClass().getName(), e);
		ErrorResponse errorResponse = new ErrorResponse(errorCode, e.getMessage());
		return ResponseEntity
			.status(errorCode.getStatus())
			.body(errorResponse);
	}

}
