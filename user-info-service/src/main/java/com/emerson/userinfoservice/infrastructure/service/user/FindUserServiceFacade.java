package com.emerson.userinfoservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.application.usecase.FindUserByIdUseCase;
import com.emerson.userinfoservice.domain.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FindUserServiceFacade {
	
	private final FindUserByIdUseCase findUserByIdUseCase;

	@Transactional
	public User getById(String id) {
		return this.findUserByIdUseCase.execute(id);
	}
	
}
