package com.emerson.authservice.infrastructure.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.usecase.user.FindUserByIdUseCase;
import com.emerson.authservice.application.usecase.user.FindUserByUsernameUseCase;
import com.emerson.authservice.application.usecase.user.GetUserRolesByIdUseCase;
import com.emerson.authservice.domain.model.User;

import lombok.AllArgsConstructor;

/**
 * Esta classe é uma fachada responsável por operações de consulta de dados de
 * usuários.
 * 
 * <p>Fornece métodos para obter informações de usuários a partir do ID ou nome de
 * usuário, bem como suas respectivas permissões (roles).</p>
 */
@Service
@AllArgsConstructor
public class FindUserServiceFacade {
	
	private final FindUserByIdUseCase findUserByIdUseCase;
	private final FindUserByUsernameUseCase findUserByUsernameUseCase;
	private final GetUserRolesByIdUseCase getUserRolesByIdUseCase;

	/**
	 * Busca um usuário com base no identificador informado.
	 *
	 * @param id o identificador único do usuário
	 * @return o usuário correspondente ao ID
	 */
	@Transactional
	public User getById(String id) {
		return this.findUserByIdUseCase.execute(id);
	}

	/**
	 * Busca um usuário com base no nome de usuário informado.
	 *
	 * @param username o nome de usuário
	 * @return o usuário correspondente ao nome de usuário
	 */
	@Transactional
	public User getByUsername(String username) {
		return this.findUserByUsernameUseCase.execute(username);
	}

	/**
	 * Recupera a lista de permissões (roles) associadas a um determinado usuário.
	 *
	 * @param id o identificador único do usuário
	 * @return uma lista de nomes de roles associadas ao usuário
	 */
	@Transactional
	public List<String> getRolesById(String id) {
		return this.getUserRolesByIdUseCase.execute(id);
	}

}
