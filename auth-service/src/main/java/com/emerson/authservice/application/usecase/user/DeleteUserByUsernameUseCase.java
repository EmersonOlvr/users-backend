package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;

/**
 * Caso de uso responsável por excluir um usuário com base no seu nome de usuário.
 *
 * <p>Executa a exclusão diretamente no repositório, sem realizar validações adicionais.</p>
 */
public class DeleteUserByUsernameUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;

	public DeleteUserByUsernameUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}
	
	/**
	 * Executa a exclusão do usuário com o nome de usuário fornecido.
	 *
	 * @param username Nome de usuário (e-mail ou identificador).
	 */
	public void execute(String username) {
		this.userRepositoryGateway.deleteByUsername(username);
	}
	
}
