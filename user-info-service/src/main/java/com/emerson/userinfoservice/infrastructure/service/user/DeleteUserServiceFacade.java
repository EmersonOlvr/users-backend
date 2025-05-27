package com.emerson.userinfoservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.application.usecase.DeleteUserByIdUseCase;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteUserServiceFacade {
	
	private final DeleteUserByIdUseCase deleteUserByIdUseCase;

	@Transactional
	public void deleteById(String id) {
		this.deleteUserByIdUseCase.execute(id);
	}

}
