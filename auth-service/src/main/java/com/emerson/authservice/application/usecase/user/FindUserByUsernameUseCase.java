package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.UserNotFoundByUsernameException;
import com.emerson.authservice.domain.model.User;

/**
 * Caso de uso responsável por buscar um usuário com base no seu nome de usuário.
 */
public class FindUserByUsernameUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	
	public FindUserByUsernameUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

	/**
	 * Executa a busca do usuário pelo nome de usuário fornecido.
	 *
	 * @param username Nome de usuário (e-mail ou identificador).
	 * @return O usuário encontrado.
	 *
	 * @throws UserNotFoundByUsernameException Se nenhum usuário for encontrado com o nome de usuário informado.
	 */
	public User execute(String username) {
		return this.userRepositoryGateway.findByUsername(username)
										.orElseThrow(UserNotFoundByUsernameException::new);
	}

}
