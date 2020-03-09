package com.bonaiva.logistica.handler;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bonaiva.logistica.controller.dto.ErrorResponse;
import com.bonaiva.logistica.service.exception.BaseException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleBaseException(final BaseException exception, final HttpServletRequest request) {
		return new ResponseEntity<>(buildErrorResponse(exception, request), exception.getStatus());
	}
	
	private static ErrorResponse buildErrorResponse(final BaseException exception, final HttpServletRequest request) {
		
		return ErrorResponse
				.builder()
				.error(exception.getStatus().name())
				.status(exception.getStatus().value())
				.message(exception.getMessage())
				.timestamp(Instant.now().toEpochMilli())
				.path(request.getServletPath())
				.build();
	}

}
