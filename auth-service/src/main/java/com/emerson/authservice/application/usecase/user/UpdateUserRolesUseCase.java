package com.emerson.authservice.application.usecase.user;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.emerson.authservice.application.gateways.RoleRepositoryGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.model.Role;
import com.emerson.authservice.domain.model.User;

public class UpdateUserRolesUseCase {
	
	private final FindUserByIdUseCase findUserByIdUseCase;
	private final UserRepositoryGateway userRepositoryGateway;
	private final RoleRepositoryGateway roleRepositoryGateway;
	
	public UpdateUserRolesUseCase(
			FindUserByIdUseCase findUserByIdUseCase, 
			UserRepositoryGateway userRepositoryGateway, 
			RoleRepositoryGateway roleRepositoryGateway) 
	{
		this.findUserByIdUseCase = findUserByIdUseCase;
		this.userRepositoryGateway = userRepositoryGateway;
		this.roleRepositoryGateway = roleRepositoryGateway;
	}

	public User execute(String id, List<String> roles) {
		User user = this.findUserByIdUseCase.execute(id);
		
		List<Role> existingRoles = this.roleRepositoryGateway.findByNameIn(roles);
		Set<String> existingNames = existingRoles.stream()
													.map(Role::getName)
													.collect(Collectors.toSet());

		List<Role> newRoles = roles.stream()
									.filter(name -> !existingNames.contains(name))
									.map(Role::new)
									.toList();

		existingRoles.addAll(this.roleRepositoryGateway.saveAll(newRoles));
		
		user.setRoles(existingRoles);
		this.userRepositoryGateway.save(user);
		
		return user;
	}

}
