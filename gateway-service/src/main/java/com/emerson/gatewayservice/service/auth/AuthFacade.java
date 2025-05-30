package com.emerson.gatewayservice.service.auth;

import org.springframework.stereotype.Service;

import com.emerson.gatewayservice.client.auth.AuthClient;
import com.emerson.gatewayservice.client.auth.dto.LoginRequest;
import com.emerson.gatewayservice.client.auth.dto.TokenDto;

/**
 * Esta classe é uma fachada responsável por encapsular o processo de autenticação de usuários
 * através da comunicação com o serviço de autenticação remoto ({@link AuthClient}).
 *
 * <p>Serve como camada intermediária entre os controladores do Gateway e o client Feign do auth-service.</p>
 */
@Service
public class AuthFacade {
	
	private final AuthClient authClient;

	public AuthFacade(AuthClient authClient) {
		this.authClient = authClient;
	}

	/**
	 * Realiza o login de um usuário por meio do serviço de autenticação.
	 *
	 * @param loginRequest objeto contendo as credenciais do usuário
	 * @return objeto com os tokens JWT de autenticação
	 */
	public TokenDto login(LoginRequest loginRequest) {
		return this.authClient.login(loginRequest);
	}
	
}
