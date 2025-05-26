package com.emerson.authservice.service.user.read;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emerson.authservice.domain.user.User;

@Service
public class FindUserServiceFacade {
	
	private final FindUserByIdUseCase findUserByIdUseCase;
	private final FindUserByUsernameUseCase findUserByUsernameUseCase;
	private final GetUserRolesByIdUseCase getUserRolesByIdUseCase;
	
	public FindUserServiceFacade(
			FindUserByIdUseCase findUserByIdUseCase, 
			FindUserByUsernameUseCase findUserByUsernameUseCase, 
			GetUserRolesByIdUseCase getUserRolesByIdUseCase) 
	{
		this.findUserByIdUseCase = findUserByIdUseCase;
		this.findUserByUsernameUseCase = findUserByUsernameUseCase;
		this.getUserRolesByIdUseCase = getUserRolesByIdUseCase;
	}

	public User getById(String id) {
		return this.findUserByIdUseCase.execute(id);
	}
	
	public User getByUsername(String username) {
		return this.findUserByUsernameUseCase.execute(username);
	}
	
	public List<String> getRolesById(String id) {
		return this.getUserRolesByIdUseCase.execute(id);
	}

}
