package com.emerson.authservice.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = -539395481117088387L;

	public InvalidCredentialsException() {
		super("E-mail e/ou senha incorretos.");
	}

}
