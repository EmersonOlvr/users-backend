package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.model.User;

public class UpdateUserUsernameUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	private final FindUserByIdUseCase findUserByIdUseCase;
	
	public UpdateUserUsernameUseCase(UserRepositoryGateway userRepositoryGateway, FindUserByIdUseCase findUserByIdUseCase) {
		this.userRepositoryGateway = userRepositoryGateway;
		this.findUserByIdUseCase = findUserByIdUseCase;
	}

	public User execute(String id, String newUsername) {
		User user = this.findUserByIdUseCase.execute(id);
		
		user.setUsername(newUsername);
		this.userRepositoryGateway.save(user);
		
		return user;
	}

}
