package com.emerson.userinfoservice.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.domain.user.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

	boolean existsByCpf(String cpf);
	
	@Modifying
	@Transactional
	@Query("UPDATE UserInfo u SET u.active = false WHERE u.id = :id")
	void deleteById(String id);

}
