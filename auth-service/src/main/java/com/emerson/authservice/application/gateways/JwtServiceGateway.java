package com.emerson.authservice.application.gateways;

import com.emerson.authservice.application.dto.TokenDto;

import com.emerson.authservice.domain.model.User;

/**
 * Interface que define o contrato para geração de tokens JWT
 * utilizados para autenticação e autorização de usuários.
 * 
 * <p>Essa interface é usada pelo auth-service para gerar tokens
 * que são consumidos pelos outros serviços do ecossistema. Ela atua
 * como uma abstração que pode ser implementada com diferentes provedores
 * de token, se necessário.</p>
 */
public interface JwtServiceGateway {
	
	/**
	 * Gera um token JWT com base nas informações do usuário fornecido.
	 *
	 * @param user O usuário autenticado para o qual o token será gerado.
	 * @return Um objeto {@link TokenDto} contendo o token JWT e informações
	 *         adicionais relacionadas à autenticação.
	 */
	TokenDto generateToken(User user);
	
}
