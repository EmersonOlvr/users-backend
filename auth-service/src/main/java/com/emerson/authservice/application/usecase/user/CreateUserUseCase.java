package com.emerson.authservice.application.usecase.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.emerson.authservice.application.gateways.PasswordServiceGateway;
import com.emerson.authservice.application.gateways.RoleRepositoryGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.BadRequestException;
import com.emerson.authservice.domain.exception.ConflictException;
import com.emerson.authservice.domain.model.Role;
import com.emerson.authservice.domain.model.User;

public class CreateUserUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	private final RoleRepositoryGateway roleRepositoryGateway;
	private final PasswordServiceGateway passwordServiceGateway;
	
	public CreateUserUseCase(UserRepositoryGateway userRepositoryGateway, 
							RoleRepositoryGateway roleRepositoryGateway, 
							PasswordServiceGateway passwordServiceGateway) 
	{
		this.userRepositoryGateway = userRepositoryGateway;
		this.roleRepositoryGateway = roleRepositoryGateway;
		this.passwordServiceGateway = passwordServiceGateway;
	}

	public User execute(String username, String password, String passwordRepeat) {
		if (!password.equals(passwordRepeat))
			throw new BadRequestException("As senhas informadas não são iguais.");
		
		if (this.userRepositoryGateway.existsByUsername(username))
			throw new ConflictException("Já existe um usuário com o e-mail/usuário informado.");
		
		List<Role> roles = new ArrayList<>(Arrays.asList(this.getOrCreateRole(Role.Values.USER.name())));
		if (this.userRepositoryGateway.count() == 0)
			roles.add(this.getOrCreateRole(Role.Values.ADMIN.name()));
		
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(this.passwordServiceGateway.encode(password));
		newUser.setRoles(roles);
		
		this.userRepositoryGateway.save(newUser);
		
		return newUser;
	}
	
	private Role getOrCreateRole(String name) {
		return this.roleRepositoryGateway.findByName(name)
									.orElseGet(() -> this.roleRepositoryGateway.save(new Role(name)));
	}
	
}
