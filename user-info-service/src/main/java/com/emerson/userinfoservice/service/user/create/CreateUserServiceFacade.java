package com.emerson.userinfoservice.service.user.create;

import org.springframework.stereotype.Service;

import com.emerson.userinfoservice.domain.user.UserInfo;
import com.emerson.userinfoservice.service.user.dto.UserDto;

@Service
public class CreateUserServiceFacade {
	
	private final CreateUserUseCase createUserUseCase;

	public CreateUserServiceFacade(CreateUserUseCase createUserUseCase) {
		this.createUserUseCase = createUserUseCase;
	}
	
	public UserInfo create(UserDto userDto) {
		return this.createUserUseCase.execute(userDto);
	}

}
