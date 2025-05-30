package com.emerson.authservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.usecase.user.CreateUserUseCase;
import com.emerson.authservice.domain.model.User;
import com.emerson.authservice.infrastructure.controller.dto.CreateUserRequest;
import com.emerson.authservice.infrastructure.messaging.kafka.producer.UserEventProducer;
import com.emerson.authservice.infrastructure.util.TransactionUtils;

import lombok.AllArgsConstructor;

/**
 * Esta classe é uma fachada responsável por orquestrar o processo de criação de
 * um novo usuário no sistema.
 * 
 * <p>Essa classe coordena o caso de uso {@link CreateUserUseCase} com a publicação
 * de eventos relacionados à criação de usuários, utilizando o {@link UserEventProducer}.</p>
 */
@Service
@AllArgsConstructor
public class CreateUserServiceFacade {
	
	private final CreateUserUseCase createUserUseCase;
	private final UserEventProducer userEventProducer;

	/**
	 * Cria um novo usuário com base nos dados fornecidos e publica um evento
	 * informando que o usuário foi criado.
	 * 
	 * <p>Esse método executa a lógica de criação do usuário e, após a confirmação
	 * da transação, dispara um evento Kafka usando {@code UserEventProducer}.</p>
	 * 
	 * <p>A publicação do evento ocorre de forma assíncrona e somente após o commit
	 * bem-sucedido da transação, utilizando {@code TransactionUtils.runAfterCommit}.</p>
	 * 
	 * @param userDto um objeto contendo os dados necessários para criar o usuário
	 * @return o usuário recém-criado
	 */
	@Transactional
	public User create(CreateUserRequest userDto) {
		User newUser = this.createUserUseCase.execute(userDto.username(), userDto.password(), userDto.passwordRepeat());
		
		// publica um evento de novo usuário criado
		TransactionUtils.runAfterCommit(() -> this.userEventProducer.sendUserCreatedEvent(newUser.getId(), newUser.getUsername()));
		
		return newUser;
	}

}
