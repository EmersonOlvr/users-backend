package com.emerson.authservice.application.gateways;

/**
 * Interface que define o contrato para codificação e verificação de senhas.
 *
 * <p>Essa interface permite abstrair o mecanismo de hashing utilizado para
 * armazenar e verificar senhas de forma segura. É especialmente útil para manter
 * o código desacoplado de implementações específicas como BCrypt, PBKDF2, etc.</p>
 */
public interface PasswordServiceGateway {
	
	/**
	 * Codifica uma senha em texto puro (raw password).
	 *
	 * @param rawPassword A senha em formato original fornecida pelo usuário.
	 * @return Uma string representando a senha codificada (hash).
	 */
	String encode(CharSequence rawPassword);

	/**
	 * Verifica se uma senha em texto puro corresponde a uma senha codificada.
	 *
	 * @param rawPassword     A senha fornecida pelo usuário (em texto puro).
	 * @param encodedPassword A senha previamente codificada.
	 * @return {@code true} se as senhas corresponderem; caso contrário, {@code false}.
	 */
	boolean matches(CharSequence rawPassword, String encodedPassword);

}
