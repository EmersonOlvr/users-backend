package com.emerson.authservice.application.usecase.user;

import java.util.List;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.model.Role;

public class GetUserRolesByIdUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;

	public GetUserRolesByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}
	
	public List<String> execute(String id) {
		return this.userRepositoryGateway.getRolesById(id)
								.stream()
								.map(Role::getName)
								.toList();
	}

}
