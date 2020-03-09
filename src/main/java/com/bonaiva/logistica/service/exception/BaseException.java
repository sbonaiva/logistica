package com.bonaiva.logistica.service.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BaseException(final String message) {
		super(message);
	}
	
	public abstract HttpStatus getStatus();
	
}
