package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;

public class DeleteUserByUsernameUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;

	public DeleteUserByUsernameUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}
	
	public void execute(String username) {
		this.userRepositoryGateway.deleteByUsername(username);
	}
	
}
