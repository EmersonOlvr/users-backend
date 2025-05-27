package com.emerson.authservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.usecase.user.DeleteUserByIdUseCase;
import com.emerson.authservice.application.usecase.user.DeleteUserByUsernameUseCase;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteUserServiceFacade {
	
	private final DeleteUserByIdUseCase deleteUserByIdUseCase;
	private final DeleteUserByUsernameUseCase deleteUserByUsernameUseCase;

	@Transactional
	public void deleteById(String id) {
		this.deleteUserByIdUseCase.execute(id);
	}

	@Transactional
	public void deleteByUsername(String username) {
		this.deleteUserByUsernameUseCase.execute(username);
	}

}
