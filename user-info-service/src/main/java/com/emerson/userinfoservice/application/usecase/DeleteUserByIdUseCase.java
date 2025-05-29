package com.emerson.userinfoservice.application.usecase;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;

/**
 * Caso de uso responsável por deletar um usuário a partir do seu ID.
 */
public class DeleteUserByIdUseCase {
	
	private final UserInfoRepositoryGateway userRepositoryGateway;

	public DeleteUserByIdUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}
	
	/**
	 * Executa a remoção do usuário com o ID informado.
	 * 
	 * @param id o identificador único do usuário a ser removido.
	 */
	public void execute(String id) {
		this.userRepositoryGateway.deleteById(id);
	}
	
}
