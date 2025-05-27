package com.emerson.userinfoservice.application.usecase;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.domain.exception.UserNotFoundByIdException;
import com.emerson.userinfoservice.domain.model.User;

public class FindUserByIdUseCase {
	
	private final UserInfoRepositoryGateway userRepositoryGateway;
	
	public FindUserByIdUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

	public User execute(String id) {
		return this.userRepositoryGateway.findById(id)
										.orElseThrow(UserNotFoundByIdException::new);
	}

}
