package com.emerson.userinfoservice.application.usecase;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.domain.exception.UserNotFoundByIdException;
import com.emerson.userinfoservice.domain.model.User;

/**
 * Caso de uso responsável por buscar um {@link User} pelo seu ID.
 * 
 * Lança uma exceção {@link UserNotFoundByIdException} caso o usuário não seja encontrado.
 */
public class FindUserByIdUseCase {
	
	private final UserInfoRepositoryGateway userRepositoryGateway;
	
	public FindUserByIdUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

	/**
	 * Executa a busca por um usuário com o ID informado.
	 * 
	 * @param id o identificador único do usuário.
	 * @return o {@link User} correspondente.
	 * 
	 * @throws UserNotFoundByIdException caso o usuário não seja encontrado.
	 */
	public User execute(String id) {
		return this.userRepositoryGateway.findById(id)
										.orElseThrow(UserNotFoundByIdException::new);
	}

}
