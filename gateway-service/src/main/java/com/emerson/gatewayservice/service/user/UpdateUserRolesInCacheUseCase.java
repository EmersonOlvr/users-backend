package com.emerson.gatewayservice.service.user;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Este caso de uso é responsável por atualizar as roles de um usuário no cache.
 *
 * <p>É acionado principalmente a partir de eventos Kafka, quando há atualização de roles no sistema de autenticação.</p>
 */
@Service
@Slf4j
public class UpdateUserRolesInCacheUseCase {
	
	/**
	 * Atualiza as roles de um usuário no cache local usando o cache
	 * {@code userRoles}.
	 *
	 * @param userId ID do usuário cujas roles devem ser atualizadas
	 * @param roles  nova lista de roles do usuário
	 * @return a lista atualizada de roles
	 */
	@CachePut(value = "userRoles", key = "#userId")
	public List<String> execute(String userId, List<String> roles) {
		log.info("UpdateUserRolesInCacheUseCase.execute( \"" + userId + "\", " + roles + " )");
		
		return roles;
	}

}
