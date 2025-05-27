package com.emerson.authservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.usecase.user.CreateUserUseCase;
import com.emerson.authservice.domain.model.User;
import com.emerson.authservice.infrastructure.controller.dto.CreateUserRequest;
import com.emerson.authservice.infrastructure.messaging.kafka.producer.UserEventProducer;
import com.emerson.authservice.infrastructure.util.TransactionUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateUserServiceFacade {
	
	private final CreateUserUseCase createUserUseCase;
	private final UserEventProducer userEventProducer;

	@Transactional
	public User create(CreateUserRequest userDto) {
		User newUser = this.createUserUseCase.execute(userDto.username(), userDto.password(), userDto.passwordRepeat());
		
		// publica um evento de novo usuário criado
		TransactionUtils.runAfterCommit(() -> this.userEventProducer.sendUserCreatedEvent(newUser.getId(), newUser.getUsername()));
		
		return newUser;
	}

}
