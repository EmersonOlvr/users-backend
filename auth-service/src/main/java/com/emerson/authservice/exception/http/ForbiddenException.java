package com.emerson.authservice.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenException extends HttpRuntimeException {

	private static final long serialVersionUID = -964591740463485763L;
	
	private static final String FORBIDDEN_MESSAGE = "Você não tem as permissões necessárias para acessar este recurso.";
	
	public ForbiddenException(String message) {
		super(message, HttpStatus.FORBIDDEN);
	}
	
	public ForbiddenException() {
		super(FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
	}

}
