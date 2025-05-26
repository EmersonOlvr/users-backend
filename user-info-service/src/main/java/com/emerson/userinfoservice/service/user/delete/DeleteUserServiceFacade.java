package com.emerson.userinfoservice.service.user.delete;

import org.springframework.stereotype.Service;

@Service
public class DeleteUserServiceFacade {
	
	private final DeleteUserByIdUseCase deleteUserByIdUseCase;

	public DeleteUserServiceFacade(DeleteUserByIdUseCase deleteUserByIdUseCase) {
		this.deleteUserByIdUseCase = deleteUserByIdUseCase;
	}
	
	public void deleteById(String id) {
		this.deleteUserByIdUseCase.execute(id);
	}

}
