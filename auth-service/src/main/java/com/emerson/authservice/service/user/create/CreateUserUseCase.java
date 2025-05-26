package com.emerson.authservice.service.user.create;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.domain.user.Role;
import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.exception.http.BadRequestException;
import com.emerson.authservice.exception.http.ConflictException;
import com.emerson.authservice.kafka.producer.UserEventProducer;
import com.emerson.authservice.repository.user.RoleRepository;
import com.emerson.authservice.repository.user.UserRepository;
import com.emerson.authservice.service.user.create.dto.CreateUserRequest;
import com.emerson.authservice.util.TransactionUtils;

@Service
public class CreateUserUseCase {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserEventProducer userEventProducer;
	
	public CreateUserUseCase(UserRepository userRepository, 
							RoleRepository roleRepository, 
							PasswordEncoder passwordEncoder, 
							UserEventProducer userEventProducer) 
	{
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.userEventProducer = userEventProducer;
	}

	@Transactional
	public User execute(CreateUserRequest userDto) {
		if (!userDto.password().equals(userDto.passwordRepeat()))
			throw new BadRequestException("As senhas informadas não são iguais.");
		
		if (this.userRepository.existsByUsername(userDto.username()))
			throw new ConflictException("Já existe um usuário com o e-mail/usuário informado.");
		
		List<Role> roles = new ArrayList<>(Arrays.asList(this.getOrCreateRole(Role.Values.USER.name())));
		if (this.userRepository.count() == 0)
			roles.add(this.getOrCreateRole(Role.Values.ADMIN.name()));
		
		User newUser = new User();
		newUser.setUsername(userDto.username());
		newUser.setPassword(this.passwordEncoder.encode(userDto.password()));
		newUser.setRoles(roles);
		
		this.userRepository.save(newUser);
		
		// publica um evento de novo usuário criado
		TransactionUtils.runAfterCommit(() -> this.userEventProducer.sendUserCreatedEvent(newUser.getId(), newUser.getUsername()));
		
		return newUser;
	}
	
	private Role getOrCreateRole(String name) {
		return this.roleRepository.findByName(name)
									.orElseGet(() -> this.roleRepository.save(new Role(name)));
	}
	
}
