package com.emerson.userinfoservice.service.user.create;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.domain.user.UserInfo;
import com.emerson.userinfoservice.exception.http.ConflictException;
import com.emerson.userinfoservice.kafka.producer.UserEventProducer;
import com.emerson.userinfoservice.repository.user.UserInfoRepository;
import com.emerson.userinfoservice.service.user.dto.UserDto;
import com.emerson.userinfoservice.util.TransactionUtils;

@Service
public class CreateUserUseCase {
	
	private final UserInfoRepository userRepository;
	private final UserEventProducer userEventProducer;
	
	public CreateUserUseCase(UserInfoRepository userRepository, UserEventProducer userEventProducer) {
		this.userRepository = userRepository;
		this.userEventProducer = userEventProducer;
	}

	@Transactional
	public UserInfo execute(UserDto userDto) {
		if (this.userRepository.existsById(userDto.id()))
			throw new ConflictException("Já existe um usuário com o ID informado.");
		
		if (this.userRepository.existsByCpf(userDto.cpf()))
			throw new ConflictException("Já existe um usuário com o CPF informado.");
		
		UserInfo newUser = new UserInfo();
		BeanUtils.copyProperties(userDto, newUser);
		
		this.userRepository.save(newUser);
		
		// publica um evento de novo usuário criado
		TransactionUtils.runAfterCommit(() -> this.userEventProducer.sendUserCreatedEvent(
				newUser.getId(), 
				newUser.getFullName(), 
				newUser.getCpf()
		));
		
		return newUser;
	}
	
}
