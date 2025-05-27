package com.emerson.userinfoservice.domain.exception;

public class UserNotFoundByIdException extends HttpRuntimeException {

	private static final long serialVersionUID = -7608239699943264894L;

	public UserNotFoundByIdException() {
		super("Não foi possível encontrar usuário com o ID informado.", 404);
	}

}
