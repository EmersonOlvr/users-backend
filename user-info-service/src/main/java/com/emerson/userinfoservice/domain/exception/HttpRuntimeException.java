package com.emerson.userinfoservice.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exceção base para representar erros em tempo de execução que devem ser associados a um código HTTP.
 *
 * <p>Esta classe pode ser estendida para criar exceções específicas com mensagens customizadas e status HTTP
 * apropriados.</p>
 *
 * <p>Por padrão, essa exceção é convertida para uma resposta HTTP correspondente ao status fornecido.</p>
 */
@Getter
@AllArgsConstructor
public class HttpRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3855223399151104729L;
	
	/**
	 * Código HTTP associado ao erro.
	 */
	private int httpStatusCode;
	
	/**
	 * Cria uma nova instância da exceção com mensagem e código HTTP.
	 *
	 * @param message        A mensagem de erro.
	 * @param httpStatusCode O código de status HTTP correspondente (ex: 404, 400, 403, etc).
	 */
	public HttpRuntimeException(String message, int httpStatusCode) {
		super(message);
		this.httpStatusCode = httpStatusCode;
	}
	
}
