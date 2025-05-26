package com.emerson.authservice.service.user.delete;

import org.springframework.stereotype.Service;

@Service
public class DeleteUserServiceFacade {
	
	private final DeleteUserByIdUseCase deleteUserByIdUseCase;
	private final DeleteUserByUsernameUseCase deleteUserByUsernameUseCase;

	public DeleteUserServiceFacade(
			DeleteUserByIdUseCase deleteUserByIdUseCase,
			DeleteUserByUsernameUseCase deleteUserByUsernameUseCase) 
	{
		this.deleteUserByIdUseCase = deleteUserByIdUseCase;
		this.deleteUserByUsernameUseCase = deleteUserByUsernameUseCase;
	}

	public void deleteById(String id) {
		this.deleteUserByIdUseCase.execute(id);
	}
	
	public void deleteByUsername(String username) {
		this.deleteUserByUsernameUseCase.execute(username);
	}

}
