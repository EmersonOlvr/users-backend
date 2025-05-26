package com.emerson.gatewayservice.service.user;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateUserRolesInCacheUseCase {
	
	@CachePut(value = "userRoles", key = "#userId")
	public List<String> execute(String userId, List<String> roles) {
		log.info("UpdateUserRolesInCacheUseCase.execute( \"" + userId + "\", " + roles + " )");
		
		return roles;
	}

}
