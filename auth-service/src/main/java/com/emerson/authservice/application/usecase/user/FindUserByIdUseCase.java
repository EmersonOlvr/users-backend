package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.UserNotFoundByIdException;
import com.emerson.authservice.domain.model.User;

public class FindUserByIdUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	
	public FindUserByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

	public User execute(String id) {
		return this.userRepositoryGateway.findByIdWithRoles(id)
								.orElseThrow(UserNotFoundByIdException::new);
	}

}
