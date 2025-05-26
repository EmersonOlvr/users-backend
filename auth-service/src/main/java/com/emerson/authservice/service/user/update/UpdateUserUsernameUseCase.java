package com.emerson.authservice.service.user.update;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.repository.user.UserRepository;
import com.emerson.authservice.service.user.read.FindUserByIdUseCase;

@Service
public class UpdateUserUsernameUseCase {
	
	private final UserRepository userRepository;
	private final FindUserByIdUseCase findUserByIdUseCase;
	
	public UpdateUserUsernameUseCase(UserRepository userRepository, FindUserByIdUseCase findUserByIdUseCase) {
		this.userRepository = userRepository;
		this.findUserByIdUseCase = findUserByIdUseCase;
	}

	@Transactional
	public User execute(String id, String newUsername) {
		User user = this.findUserByIdUseCase.execute(id);
		
		user.setUsername(newUsername);
		this.userRepository.save(user);
		
		return user;
	}

}
