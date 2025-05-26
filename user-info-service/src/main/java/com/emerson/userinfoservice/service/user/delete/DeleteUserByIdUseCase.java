package com.emerson.userinfoservice.service.user.delete;

import org.springframework.stereotype.Service;

import com.emerson.userinfoservice.repository.user.UserInfoRepository;

@Service
public class DeleteUserByIdUseCase {
	
	private final UserInfoRepository userRepository;

	public DeleteUserByIdUseCase(UserInfoRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void execute(String id) {
		this.userRepository.deleteById(id);
	}
	
}
