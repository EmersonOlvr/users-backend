package com.emerson.authservice.domain.exception;

/**
 * Exceção lançada quando as credenciais fornecidas para autenticação estão incorretas.
 *
 * <p>Equivale ao código HTTP 400 (Bad Request).</p>
 *
 * <p>Exemplo: senha ou e-mail inválidos durante login.</p>
 */
public class InvalidCredentialsException extends HttpRuntimeException {

	private static final long serialVersionUID = -539395481117088387L;

	/**
	 * Cria uma nova instância da exceção com a mensagem padrão de credenciais inválidas.
	 */
	public InvalidCredentialsException() {
		super("E-mail e/ou senha incorretos.", 400);
	}

}
