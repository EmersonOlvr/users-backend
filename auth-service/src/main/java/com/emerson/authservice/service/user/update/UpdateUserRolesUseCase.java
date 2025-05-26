package com.emerson.authservice.service.user.update;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.domain.user.Role;
import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.kafka.producer.UserEventProducer;
import com.emerson.authservice.repository.user.RoleRepository;
import com.emerson.authservice.repository.user.UserRepository;
import com.emerson.authservice.service.user.read.FindUserByIdUseCase;
import com.emerson.authservice.util.TransactionUtils;

@Service
public class UpdateUserRolesUseCase {
	
	private final FindUserByIdUseCase findUserByIdUseCase;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserEventProducer userEventProducer;
	
	public UpdateUserRolesUseCase(
			FindUserByIdUseCase findUserByIdUseCase, 
			UserRepository userRepository, 
			RoleRepository roleRepository, 
			UserEventProducer userEventProducer) 
	{
		this.findUserByIdUseCase = findUserByIdUseCase;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userEventProducer = userEventProducer;
	}

	@Transactional
	public User execute(String id, List<String> roles) {
		User user = this.findUserByIdUseCase.execute(id);
		
		List<Role> existingRoles = this.roleRepository.findByNameIn(roles);
		Set<String> existingNames = existingRoles.stream().map(Role::getName).collect(Collectors.toSet());

		List<Role> newRoles = roles.stream()
									.filter(name -> !existingNames.contains(name))
									.map(Role::new)
									.toList();

		existingRoles.addAll(this.roleRepository.saveAll(newRoles));
		
		user.setRoles(existingRoles);
		this.userRepository.save(user);
		
		// publica um evento com as novas roles desse usuário
		TransactionUtils.runAfterCommit(() -> this.userEventProducer.sendUserRolesUpdatedEvent(id, roles));
		
		return user;
	}

}
