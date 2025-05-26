package com.emerson.authservice.service.user.update;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.exception.http.BadRequestException;
import com.emerson.authservice.repository.user.UserRepository;
import com.emerson.authservice.service.user.read.FindUserByIdUseCase;

@Service
public class UpdateUserPasswordUseCase {
	
	private final UserRepository userRepository;
	private final FindUserByIdUseCase findUserByIdUseCase;
	private final PasswordEncoder passwordEncoder;
	
	public UpdateUserPasswordUseCase(
			UserRepository userRepository, 
			FindUserByIdUseCase findUserByIdUseCase, 
			PasswordEncoder passwordEncoder) 
	{
		this.userRepository = userRepository;
		this.findUserByIdUseCase = findUserByIdUseCase;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public User execute(String id, String currentPassword, String newPassword, String newPasswordRepeat) {
		User user = this.findUserByIdUseCase.execute(id);
		
		if (currentPassword != null && !this.passwordEncoder.matches(currentPassword, user.getPassword()))
			throw new BadRequestException("A senha atual informada está incorreta.");
		
		if (!newPassword.equals(newPasswordRepeat))
			throw new BadRequestException("As novas senhas informadas não são iguais.");
		
		String hashedPassword = this.passwordEncoder.encode(newPassword);
		user.setPassword(hashedPassword);
		this.userRepository.save(user);
		
		return user;
	}

}
