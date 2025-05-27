package com.emerson.authservice.domain.exception;

public class UserNotFoundByUsernameException extends HttpRuntimeException {

	private static final long serialVersionUID = -7202337953357629632L;

	public UserNotFoundByUsernameException() {
		super("Não foi possível encontrar usuário com o e-mail/usuário informado.", 404);
	}

}
