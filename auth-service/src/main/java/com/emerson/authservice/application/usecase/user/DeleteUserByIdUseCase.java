package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;

public class DeleteUserByIdUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;

	public DeleteUserByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}
	
	public void execute(String id) {
		this.userRepositoryGateway.deleteById(id);
	}
	
}
