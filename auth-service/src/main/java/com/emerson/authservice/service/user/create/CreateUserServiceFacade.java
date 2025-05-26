package com.emerson.authservice.service.user.create;

import org.springframework.stereotype.Service;

import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.service.user.create.dto.CreateUserRequest;

@Service
public class CreateUserServiceFacade {
	
	private final CreateUserUseCase createUserUseCase;

	public CreateUserServiceFacade(CreateUserUseCase createUserUseCase) {
		this.createUserUseCase = createUserUseCase;
	}
	
	public User create(CreateUserRequest userDto) {
		return this.createUserUseCase.execute(userDto);
	}

}
