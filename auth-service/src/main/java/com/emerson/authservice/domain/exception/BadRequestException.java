package com.emerson.authservice.domain.exception;

public class BadRequestException extends HttpRuntimeException {

	private static final long serialVersionUID = 3984893269015753276L;

	public BadRequestException(String message) {
		super(message, 400);
	}

}
