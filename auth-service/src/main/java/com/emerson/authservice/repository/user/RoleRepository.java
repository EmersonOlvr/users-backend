package com.emerson.authservice.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emerson.authservice.domain.user.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	
	List<Role> findByNameIn(List<String> names);

	Optional<Role> findByName(String name);

}
