package com.emerson.authservice.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedRequestException extends HttpRuntimeException {

	private static final long serialVersionUID = -7629182340183666776L;
	
	public static final String UNAUTHORIZED_MESSAGE = "Forneça um token válido para acessar este recurso.";

	public UnauthorizedRequestException(String message) {
		super(message, HttpStatus.UNAUTHORIZED);
	}
	
	public UnauthorizedRequestException() {
		super(UNAUTHORIZED_MESSAGE, HttpStatus.UNAUTHORIZED);
	}

}
