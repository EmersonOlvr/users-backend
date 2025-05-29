package com.emerson.authservice.application.usecase.user;

import java.util.List;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.model.Role;

/**
 * Caso de uso responsável por recuperar os nomes das roles atribuídas a um usuário.
 *
 * <p>Consulta o repositório de usuários e retorna apenas os nomes das roles
 * associadas ao usuário identificado pelo ID.</p>
 */
public class GetUserRolesByIdUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;

	public GetUserRolesByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		this.userRepositoryGateway = userRepositoryGateway;
	}

	/**
	 * Executa a busca pelas roles do usuário.
	 *
	 * @param id Identificador do usuário.
	 * @return Lista com os nomes das roles atribuídas ao usuário.
	 */
	public List<String> execute(String id) {
		return this.userRepositoryGateway.getRolesById(id)
								.stream()
								.map(Role::getName)
								.toList();
	}

}
