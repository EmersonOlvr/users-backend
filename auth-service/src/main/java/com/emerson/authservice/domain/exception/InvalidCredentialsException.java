package com.emerson.authservice.domain.exception;

public class InvalidCredentialsException extends HttpRuntimeException {

	private static final long serialVersionUID = -539395481117088387L;

	public InvalidCredentialsException() {
		super("E-mail e/ou senha incorretos.", 400);
	}

}
