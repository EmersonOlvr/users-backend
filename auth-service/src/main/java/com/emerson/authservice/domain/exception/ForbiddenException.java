package com.emerson.authservice.domain.exception;

public class ForbiddenException extends HttpRuntimeException {

	private static final long serialVersionUID = -964591740463485763L;
	
	private static final String FORBIDDEN_MESSAGE = "Você não tem as permissões necessárias para acessar este recurso.";
	
	public ForbiddenException(String message) {
		super(message, 403);
	}
	
	public ForbiddenException() {
		super(FORBIDDEN_MESSAGE, 403);
	}

}
