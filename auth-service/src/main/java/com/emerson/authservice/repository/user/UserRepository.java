package com.emerson.authservice.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.domain.user.Role;
import com.emerson.authservice.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	boolean existsByUsername(String username);
	Optional<User> findByUsername(String username);
	
	@Query("SELECT r FROM User u JOIN u.roles r WHERE u.id = :id")
	List<Role> getRolesById(String id);
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id")
	Optional<User> findByIdWithRoles(String id);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.active = false WHERE u.username = :username")
	void deleteByUsername(String username);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.active = false WHERE u.id = :id")
	void deleteById(String id);
	
}
