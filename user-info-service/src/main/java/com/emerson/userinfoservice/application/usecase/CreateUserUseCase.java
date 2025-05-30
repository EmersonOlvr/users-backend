package com.emerson.userinfoservice.application.usecase;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.domain.exception.ConflictException;
import com.emerson.userinfoservice.domain.model.User;

/**
 * Caso de uso responsável pela criação de um novo {@link User}.
 * 
 * Realiza validações para garantir que o ID e o CPF fornecidos não estejam
 * em uso antes de persistir a nova entidade no repositório.
 * 
 * Em caso de conflito (ID ou CPF já existentes), uma {@link ConflictException} é lançada.
 */
public class CreateUserUseCase {
	
	private final UserInfoRepositoryGateway userRepositoryGateway;
	
	public CreateUserUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

	/**
	 * Executa a criação de um novo usuário.
	 * 
	 * @param id       identificador único do usuário.
	 * @param fullName nome completo do usuário.
	 * @param cpf      CPF do usuário.
	 * @return o novo {@link User} criado e persistido.
	 * 
	 * @throws ConflictException se já existir um usuário com o mesmo ID ou CPF.
	 */
	public User execute(String id, String fullName, String cpf) {
		if (this.userRepositoryGateway.existsById(id))
			throw new ConflictException("Já existe um usuário com o ID informado.");
		
		if (this.userRepositoryGateway.existsByCpf(cpf))
			throw new ConflictException("Já existe um usuário com o CPF informado.");
		
		User newUser = new User(id, true, fullName, cpf);
		this.userRepositoryGateway.save(newUser);
		
		return newUser;
	}
	
}
