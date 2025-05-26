package com.emerson.userinfoservice.service.user.read;

import org.springframework.stereotype.Service;

import com.emerson.userinfoservice.domain.user.UserInfo;

@Service
public class FindUserServiceFacade {
	
	private final FindUserByIdUseCase findUserByIdUseCase;
	
	public FindUserServiceFacade(FindUserByIdUseCase findUserByIdUseCase) {
		this.findUserByIdUseCase = findUserByIdUseCase;
	}

	public UserInfo getById(String id) {
		return this.findUserByIdUseCase.execute(id);
	}
	
}
