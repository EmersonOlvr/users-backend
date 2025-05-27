package com.emerson.authservice.application.gateways;

import java.util.List;
import java.util.Optional;

import com.emerson.authservice.domain.model.Role;

public interface RoleRepositoryGateway {

	Role save(Role role);
	List<Role> saveAll(List<Role> newRoles);
	
	List<Role> findByNameIn(List<String> names);
	Optional<Role> findByName(String name);
	
}
