package com.emerson.authservice.service.user.read;

import org.springframework.stereotype.Service;

import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.exception.http.NotFoundException;
import com.emerson.authservice.repository.user.UserRepository;

@Service
public class FindUserByIdUseCase {
	
	private final UserRepository userRepository;
	
	public FindUserByIdUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User execute(String id) {
		return this.userRepository.findByIdWithRoles(id)
								.orElseThrow(() -> new NotFoundException("usuário", id));
	}

}
