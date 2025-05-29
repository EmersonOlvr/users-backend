package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.model.User;

/**
 * Caso de uso responsável por atualizar o nome de usuário (username) de um usuário existente.
 *
 * <p>Localiza o usuário pelo ID e atualiza o campo de username.</p>
 */
public class UpdateUserUsernameUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	private final FindUserByIdUseCase findUserByIdUseCase;
	
	public UpdateUserUsernameUseCase(UserRepositoryGateway userRepositoryGateway, FindUserByIdUseCase findUserByIdUseCase) {
		this.userRepositoryGateway = userRepositoryGateway;
		this.findUserByIdUseCase = findUserByIdUseCase;
	}

	/**
	 * Executa a atualização do nome de usuário.
	 *
	 * @param id          Identificador do usuário.
	 * @param newUsername Novo nome de usuário.
	 * @return O usuário com o nome de usuário atualizado.
	 */
	public User execute(String id, String newUsername) {
		User user = this.findUserByIdUseCase.execute(id);
		
		user.setUsername(newUsername);
		this.userRepositoryGateway.save(user);
		
		return user;
	}

}
