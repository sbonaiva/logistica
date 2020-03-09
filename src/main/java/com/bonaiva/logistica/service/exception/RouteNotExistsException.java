package com.bonaiva.logistica.service.exception;

import org.springframework.http.HttpStatus;

public class RouteNotExistsException extends BaseException {

	private static final long serialVersionUID = 1L;

	public RouteNotExistsException(final String message) {
		super(message);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
