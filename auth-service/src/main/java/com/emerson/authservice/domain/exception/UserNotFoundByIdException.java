package com.emerson.authservice.domain.exception;

/**
 * Exceção lançada quando um usuário não é encontrado pelo ID fornecido.
 *
 * <p>Equivale ao código HTTP 404 (Not Found).</p>
 */
public class UserNotFoundByIdException extends HttpRuntimeException {

	private static final long serialVersionUID = -7608239699943264894L;

	/**
	 * Cria uma nova instância da exceção com a mensagem padrão para usuário não
	 * encontrado por ID.
	 */
	public UserNotFoundByIdException() {
		super("Não foi possível encontrar usuário com o ID informado.", 404);
	}

}
