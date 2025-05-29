package com.emerson.authservice.domain.exception;

/**
 * Exceção lançada quando o usuário não possui as permissões necessárias para acessar determinado recurso.
 *
 * <p>Equivale ao código HTTP 403 (Forbidden).</p>
 */
public class ForbiddenException extends HttpRuntimeException {

	private static final long serialVersionUID = -964591740463485763L;
	
	private static final String FORBIDDEN_MESSAGE = "Você não tem as permissões necessárias para acessar este recurso.";

	/**
	 * Cria uma nova instância da exceção com uma mensagem customizada.
	 *
	 * @param message A mensagem descrevendo o erro de permissão.
	 */
	public ForbiddenException(String message) {
		super(message, 403);
	}

	/**
	 * Cria uma nova instância da exceção com a mensagem padrão de acesso negado.
	 */
	public ForbiddenException() {
		super(FORBIDDEN_MESSAGE, 403);
	}

}
