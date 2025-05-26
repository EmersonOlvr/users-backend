package com.emerson.authservice.service.user.read;

import org.springframework.stereotype.Service;

import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.exception.http.NotFoundException;
import com.emerson.authservice.repository.user.UserRepository;

@Service
public class FindUserByUsernameUseCase {
	
	private final UserRepository userRepository;
	
	public FindUserByUsernameUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User execute(String username) {
		return this.userRepository.findByUsername(username)
								.orElseThrow(() -> new NotFoundException("usuário", "o e-mail/usuário", username));
	}

}
