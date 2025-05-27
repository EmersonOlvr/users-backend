package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.UserNotFoundByUsernameException;
import com.emerson.authservice.domain.model.User;

public class FindUserByUsernameUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	
	public FindUserByUsernameUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

	public User execute(String username) {
		return this.userRepositoryGateway.findByUsername(username)
								.orElseThrow(UserNotFoundByUsernameException::new);
	}

}
