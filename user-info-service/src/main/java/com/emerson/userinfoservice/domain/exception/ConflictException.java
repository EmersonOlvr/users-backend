package com.emerson.userinfoservice.domain.exception;

public class ConflictException extends HttpRuntimeException {

	private static final long serialVersionUID = -7742033817392207878L;

	public ConflictException(String message) {
		super(message, 409);
	}

}
