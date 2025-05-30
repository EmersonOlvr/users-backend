package com.emerson.authservice.infrastructure.service.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.dto.TokenDto;
import com.emerson.authservice.application.usecase.auth.LoginWithUsernameAndPasswordUseCase;

import lombok.AllArgsConstructor;

/**
 * Esta classe atua como uma fachada para os casos de uso de autenticação.
 * 
 * <p>Ela encapsula a lógica de autenticação do sistema, delegando as operações
 * principais para os respectivos casos de uso da camada de aplicação. Essa abordagem
 * ajuda a manter o código desacoplado e facilita a orquestração de múltiplos serviços
 * ou regras em um único ponto de entrada.</p>
 */
@Service
@AllArgsConstructor
public class AuthServiceFacade {
	
	private final LoginWithUsernameAndPasswordUseCase loginWithUsernameAndPasswordUseCase;

	/**
	 * Realiza o processo de login a partir de um nome de usuário e senha.
	 * 
	 * <p>Esse método delega a autenticação ao caso de uso {@code LoginWithUsernameAndPasswordUseCase},
	 * e retorna um {@link TokenDto} contendo o token de acesso.</p>
	 * 
	 * <p>O método é anotado com {@code @Transactional}, garantindo que eventuais operações
	 * envolvidas no processo de login sejam feitas em uma transação atômica.</p>
	 * 
	 * @param username o nome de usuário informado pelo cliente
	 * @param password a senha correspondente
	 * @return um {@code TokenDto} com o token de autenticação
	 */
	@Transactional
	public TokenDto loginWithUsernameAndPassword(String username, String password) {
		return this.loginWithUsernameAndPasswordUseCase.execute(username, password);
	}

}
