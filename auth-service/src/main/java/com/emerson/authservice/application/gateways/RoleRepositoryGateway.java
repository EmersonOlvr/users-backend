package com.emerson.authservice.application.gateways;

import java.util.List;
import java.util.Optional;

import com.emerson.authservice.domain.model.Role;

/**
 * Interface de acesso a dados para entidades do tipo {@link Role}.
 *
 * <p>Define operações de persistência e consulta relacionadas aos papéis (roles)
 * atribuídos aos usuários do sistema. Essa interface atua como uma camada de
 * abstração entre o domínio e a tecnologia de persistência utilizada, como JPA,
 * MongoDB, etc.</p>
 *
 * <p>Utilizada principalmente durante a criação de usuários, autenticação e
 * controle de permissões.</p>
 */
public interface RoleRepositoryGateway {

	Role save(Role role);
	List<Role> saveAll(List<Role> newRoles);
	
	List<Role> findByNameIn(List<String> names);
	Optional<Role> findByName(String name);
	
}
