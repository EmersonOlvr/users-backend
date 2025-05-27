package com.emerson.userinfoservice.application.usecase;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;

public class DeleteUserByIdUseCase {
	
	private final UserInfoRepositoryGateway userRepositoryGateway;

	public DeleteUserByIdUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}
	
	public void execute(String id) {
		this.userRepositoryGateway.deleteById(id);
	}
	
}
