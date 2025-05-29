package com.emerson.authservice.domain.exception;

/**
 * Exceção lançada quando um usuário não é encontrado pelo e-mail ou nome de usuário fornecido.
 *
 * <p>Equivale ao código HTTP 404 (Not Found).</p>
 */
public class UserNotFoundByUsernameException extends HttpRuntimeException {

	private static final long serialVersionUID = -7202337953357629632L;

	/**
	 * Cria uma nova instância da exceção com a mensagem padrão para usuário não
	 * encontrado por e-mail/username.
	 */
	public UserNotFoundByUsernameException() {
		super("Não foi possível encontrar usuário com o e-mail/usuário informado.", 404);
	}

}
