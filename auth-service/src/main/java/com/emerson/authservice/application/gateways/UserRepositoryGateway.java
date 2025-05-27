package com.emerson.authservice.application.gateways;

import java.util.List;
import java.util.Optional;

import com.emerson.authservice.domain.model.Role;
import com.emerson.authservice.domain.model.User;

public interface UserRepositoryGateway {

	User save(User user);
	
	boolean existsByUsername(String username);
	
	Optional<User> findById(String userId);
	Optional<User> findByIdWithRoles(String id);
	Optional<User> findByUsername(String username);

	List<Role> getRolesById(String id);
	
	void deleteByUsername(String username);
	void deleteById(String id);

	long count();
	
}
