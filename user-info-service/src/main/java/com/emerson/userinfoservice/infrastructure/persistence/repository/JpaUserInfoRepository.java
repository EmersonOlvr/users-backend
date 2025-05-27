package com.emerson.userinfoservice.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.infrastructure.persistence.entity.JpaUserInfo;

public interface JpaUserInfoRepository extends JpaRepository<JpaUserInfo, String> {

	boolean existsByCpf(String cpf);
	
	@Modifying
	@Transactional
	@Query("UPDATE JpaUserInfo u SET u.active = false WHERE u.id = :id")
	void deleteById(String id);

}
