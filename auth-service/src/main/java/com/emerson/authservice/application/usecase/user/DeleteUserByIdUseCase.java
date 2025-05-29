package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;

/**
 * Caso de uso responsável por excluir um usuário com base no seu ID.
 *
 * <p>Executa a exclusão diretamente no repositório, sem realizar validações adicionais.</p>
 */
public class DeleteUserByIdUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;

	public DeleteUserByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}
	
	/**
	 * Executa a exclusão do usuário com o ID fornecido.
	 *
	 * @param id Identificador único do usuário.
	 */
	public void execute(String id) {
		this.userRepositoryGateway.deleteById(id);
	}
	
}
