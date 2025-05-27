package com.emerson.authservice.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emerson.authservice.infrastructure.persistence.entity.JpaRole;

public interface JpaRoleRepository extends JpaRepository<JpaRole, String> {
	
	List<JpaRole> findByNameIn(List<String> names);
	Optional<JpaRole> findByName(String name);

}
