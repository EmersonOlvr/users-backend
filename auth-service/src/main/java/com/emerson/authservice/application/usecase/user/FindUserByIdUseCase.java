package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.UserNotFoundByIdException;
import com.emerson.authservice.domain.model.User;

/**
 * Caso de uso responsável por buscar um usuário com base no seu ID.
 *
 * <p>Também recupera os papéis (roles) associados ao usuário.</p>
 */
public class FindUserByIdUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	
	public FindUserByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

	/**
	 * Executa a busca do usuário pelo ID fornecido.
	 *
	 * @param id Identificador único do usuário.
	 * @return O usuário encontrado.
	 *
	 * @throws UserNotFoundByIdException Se nenhum usuário for encontrado com o ID informado.
	 */
	public User execute(String id) {
		return this.userRepositoryGateway.findByIdWithRoles(id)
										.orElseThrow(UserNotFoundByIdException::new);
	}

}
