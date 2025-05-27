package com.emerson.userinfoservice.application.usecase;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.domain.model.User;

public class UpdateUserPersonalInfoUseCase {
	
	private final UserInfoRepositoryGateway userRepositoryGateway;
	private final FindUserByIdUseCase findUserByIdUseCase;
	
	public UpdateUserPersonalInfoUseCase(UserInfoRepositoryGateway userRepositoryGateway, FindUserByIdUseCase findUserByIdUseCase) {
		this.userRepositoryGateway = userRepositoryGateway;
		this.findUserByIdUseCase = findUserByIdUseCase;
	}

	public User execute(String id, String fullName, String cpf) {
		User user = this.findUserByIdUseCase.execute(id);
		
		user.setFullName(fullName);
		user.setCpf(cpf);
		this.userRepositoryGateway.save(user);
		
		return user;
	}

}
