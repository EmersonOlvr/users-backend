package com.emerson.userinfoservice.service.user.read;

import org.springframework.stereotype.Service;

import com.emerson.userinfoservice.domain.user.UserInfo;
import com.emerson.userinfoservice.exception.http.NotFoundException;
import com.emerson.userinfoservice.repository.user.UserInfoRepository;

@Service
public class FindUserByIdUseCase {
	
	private final UserInfoRepository userRepository;
	
	public FindUserByIdUseCase(UserInfoRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserInfo execute(String id) {
		return this.userRepository.findById(id)
								.orElseThrow(() -> new NotFoundException("usuário", id));
	}

}
