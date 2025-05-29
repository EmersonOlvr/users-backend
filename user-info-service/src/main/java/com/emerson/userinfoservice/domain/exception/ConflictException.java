package com.emerson.userinfoservice.domain.exception;

/**
 * Exceção lançada quando há um conflito na requisição, como tentativa de criar um recurso já existente.
 *
 * <p>Equivale ao código HTTP 409 (Conflict).</p>
 *
 * <p>Exemplo: cadastro de usuário com e-mail já existente.</p>
 */
public class ConflictException extends HttpRuntimeException {

	private static final long serialVersionUID = -7742033817392207878L;

	/**
	 * Cria uma nova instância da exceção de conflito com a mensagem fornecida.
	 *
	 * @param message A mensagem descrevendo o motivo do conflito.
	 */
	public ConflictException(String message) {
		super(message, 409);
	}

}
