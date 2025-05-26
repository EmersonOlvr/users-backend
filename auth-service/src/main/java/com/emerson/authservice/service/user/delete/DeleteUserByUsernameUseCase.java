package com.emerson.authservice.service.user.delete;

import org.springframework.stereotype.Service;

import com.emerson.authservice.repository.user.UserRepository;

@Service
public class DeleteUserByUsernameUseCase {
	
	private final UserRepository userRepository;

	public DeleteUserByUsernameUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void execute(String username) {
		this.userRepository.deleteByUsername(username);
	}
	
}
