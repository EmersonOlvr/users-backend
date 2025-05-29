package com.emerson.authservice.domain.exception;

/**
 * Exceção lançada quando uma requisição contém dados inválidos ou malformados.
 *
 * <p>Geralmente usada para representar erros de validação ou pré-condições
 * não atendidas em chamadas de métodos em casos de uso.</p>
 *
 * <p>Exemplos de uso incluem:
 * <ul>
 *     <li>Senhas que não coincidem;</li>
 *     <li>Formato inválido de e-mail ou campos obrigatórios ausentes;</li>
 *     <li>Operações não permitidas de acordo com as regras de negócio.</li>
 * </ul>
 * </p>
 *
 * <p>Equivale ao erro HTTP 400 (Bad Request).</p>
 */
public class BadRequestException extends HttpRuntimeException {

	private static final long serialVersionUID = 3984893269015753276L;

	/**
	 * Cria uma nova instância da exceção com a mensagem fornecida.
	 *
	 * @param message A mensagem descrevendo o erro.
	 */
	public BadRequestException(String message) {
		super(message, 400);
	}

}
