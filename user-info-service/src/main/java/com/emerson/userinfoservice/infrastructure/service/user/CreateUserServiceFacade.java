package com.emerson.userinfoservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.application.usecase.CreateUserUseCase;
import com.emerson.userinfoservice.domain.model.User;
import com.emerson.userinfoservice.infrastructure.controller.dto.UserDto;
import com.emerson.userinfoservice.infrastructure.messaging.kafka.producer.UserEventProducer;
import com.emerson.userinfoservice.infrastructure.util.TransactionUtils;

import lombok.AllArgsConstructor;

/**
 * Esta classe é uma fachada responsável por orquestrar o processo de criação de um novo usuário.
 *
 * <p>Ela utiliza o caso de uso {@link CreateUserUseCase} para executar as regras de negócio
 * relacionadas à criação do usuário e o {@link UserEventProducer} para publicar um evento
 * após a persistência bem-sucedida do usuário.</p>
 */
@Service
@AllArgsConstructor
public class CreateUserServiceFacade {
	
	private final CreateUserUseCase createUserUseCase;
	private final UserEventProducer userEventProducer;

	/**
	 * Cria um novo usuário a partir dos dados fornecidos e publica um evento após o
	 * commit da transação.
	 *
	 * @param userDto dados do novo usuário
	 * @return o usuário criado
	 */
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
