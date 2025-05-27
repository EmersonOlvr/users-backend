package com.emerson.userinfoservice.application.usecase;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.domain.exception.ConflictException;
import com.emerson.userinfoservice.domain.model.User;

public class CreateUserUseCase {
	
	private final UserInfoRepositoryGateway userRepositoryGateway;
	
	public CreateUserUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

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
