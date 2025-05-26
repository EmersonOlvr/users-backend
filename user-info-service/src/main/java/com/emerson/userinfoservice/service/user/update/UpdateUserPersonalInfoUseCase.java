package com.emerson.userinfoservice.service.user.update;

import org.springframework.stereotype.Service;

import com.emerson.userinfoservice.domain.user.UserInfo;
import com.emerson.userinfoservice.repository.user.UserInfoRepository;
import com.emerson.userinfoservice.service.user.read.FindUserByIdUseCase;
import com.emerson.userinfoservice.service.user.update.dto.UpdatePersonalInfoRequest;

@Service
public class UpdateUserPersonalInfoUseCase {
	
	private final UserInfoRepository userRepository;
	private final FindUserByIdUseCase findUserByIdUseCase;
	
	public UpdateUserPersonalInfoUseCase(UserInfoRepository userRepository, FindUserByIdUseCase findUserByIdUseCase) {
		this.userRepository = userRepository;
		this.findUserByIdUseCase = findUserByIdUseCase;
	}

	public UserInfo execute(String id, UpdatePersonalInfoRequest personalInfoRequest) {
		UserInfo user = this.findUserByIdUseCase.execute(id);
		
		user.setFullName(personalInfoRequest.fullName());
		user.setCpf(personalInfoRequest.cpf());
		this.userRepository.save(user);
		
		return user;
	}

}
