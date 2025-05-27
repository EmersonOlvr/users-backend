package com.emerson.userinfoservice.application.gateways;

import java.util.Optional;

import com.emerson.userinfoservice.domain.model.User;

public interface UserInfoRepositoryGateway {

	User save(User user);
	
	boolean existsById(String id);
	boolean existsByCpf(String cpf);

	Optional<User> findById(String id);
	
	void deleteById(String id);
	
}
