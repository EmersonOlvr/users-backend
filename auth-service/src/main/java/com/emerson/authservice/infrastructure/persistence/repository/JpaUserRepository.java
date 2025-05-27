package com.emerson.authservice.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.infrastructure.persistence.entity.JpaRole;
import com.emerson.authservice.infrastructure.persistence.entity.JpaUser;

public interface JpaUserRepository extends JpaRepository<JpaUser, String> {
	
	boolean existsByUsername(String username);
	Optional<JpaUser> findByUsername(String username);
	
	@Query("SELECT r FROM JpaUser u JOIN u.roles r WHERE u.id = :id")
	List<JpaRole> getRolesById(String id);
	
	@Query("SELECT u FROM JpaUser u LEFT JOIN FETCH u.roles WHERE u.id = :id")
	Optional<JpaUser> findByIdWithRoles(String id);
	
	@Modifying
	@Transactional
	@Query("UPDATE JpaUser u SET u.active = false WHERE u.username = :username")
	void deleteByUsername(String username);
	
	@Modifying
	@Transactional
	@Query("UPDATE JpaUser u SET u.active = false WHERE u.id = :id")
	void deleteById(String id);
	
}
