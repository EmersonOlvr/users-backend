package com.emerson.userinfoservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.application.usecase.CreateUserUseCase;
import com.emerson.userinfoservice.domain.model.User;
import com.emerson.userinfoservice.infrastructure.controller.dto.UserDto;
import com.emerson.userinfoservice.infrastructure.messaging.kafka.producer.UserEventProducer;
import com.emerson.userinfoservice.infrastructure.util.TransactionUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateUserServiceFacade {
	
	private final CreateUserUseCase createUserUseCase;
	private final UserEventProducer userEventProducer;

	@Transactional
	public User create(UserDto userDto) {
		User newUser = this.createUserUseCase.execute(userDto.id(), userDto.fullName(), userDto.cpf());
		
		// publica um evento de novo usuário criado
		TransactionUtils.runAfterCommit(() -> this.userEventProducer.sendUserCreatedEvent(
				newUser.getId(), 
				newUser.getFullName(), 
				newUser.getCpf()
		));
		
		return newUser;
	}

}
