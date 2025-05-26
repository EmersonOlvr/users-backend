package com.emerson.authservice.service.user.read;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emerson.authservice.domain.user.Role;
import com.emerson.authservice.repository.user.UserRepository;

@Service
public class GetUserRolesByIdUseCase {
	
	private final UserRepository userRepository;

	public GetUserRolesByIdUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<String> execute(String id) {
		return this.userRepository.getRolesById(id).stream().map(Role::getName).toList();
	}

}
