package com.emerson.authservice.service.user.delete;

import org.springframework.stereotype.Service;

import com.emerson.authservice.repository.user.UserRepository;

@Service
public class DeleteUserByIdUseCase {
	
	private final UserRepository userRepository;

	public DeleteUserByIdUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void execute(String id) {
		this.userRepository.deleteById(id);
	}
	
}
