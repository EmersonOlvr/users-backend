package com.emerson.authservice.application.gateways;

import java.util.List;
import java.util.Optional;

import com.emerson.authservice.domain.model.Role;
import com.emerson.authservice.domain.model.User;

/**
 * Interface de acesso a dados para entidades do tipo {@link User}.
 *
 * <p>Responsável por definir as operações relacionadas à persistência, busca,
 * exclusão e contagem de usuários. Também permite recuperar os papéis
 * (roles) associados a um usuário.</p>
 *
 * <p>Essa interface isola a lógica de acesso a dados, facilitando a substituição
 * ou mock de repositórios em testes e mantendo o domínio desacoplado da camada de
 * infraestrutura.</p>
 */
public interface UserRepositoryGateway {

	User save(User user);
	
	boolean existsByUsername(String username);
	
	Optional<User> findById(String userId);
	Optional<User> findByIdWithRoles(String id);
	Optional<User> findByUsername(String username);

	List<Role> getRolesById(String id);
	
	void deleteByUsername(String username);
	void deleteById(String id);

	long count();
	
}
