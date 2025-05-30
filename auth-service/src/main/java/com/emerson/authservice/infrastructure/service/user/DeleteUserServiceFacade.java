package com.emerson.authservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.usecase.user.DeleteUserByIdUseCase;
import com.emerson.authservice.application.usecase.user.DeleteUserByUsernameUseCase;

import lombok.AllArgsConstructor;

/**
 * Esta classe é uma fachada responsável por orquestrar as operações de exclusão
 * de usuários no sistema.
 * 
 * <p>Expõe métodos de alto nível para remover usuários, seja pelo seu
 * identificador único (ID) ou pelo nome de usuário (username), delegando a
 * lógica de negócio para os respectivos casos de uso.</p>
 */
@Service
@AllArgsConstructor
public class DeleteUserServiceFacade {
	
	private final DeleteUserByIdUseCase deleteUserByIdUseCase;
	private final DeleteUserByUsernameUseCase deleteUserByUsernameUseCase;

	/**
	 * Exclui um usuário com base no identificador informado.
	 * 
	 * <p>Este método delega a exclusão ao caso de uso {@link DeleteUserByIdUseCase}.</p>
	 *
	 * @param id o identificador único do usuário a ser removido
	 */
	@Transactional
	public void deleteById(String id) {
		this.deleteUserByIdUseCase.execute(id);
	}

	/**
	 * Exclui um usuário com base no nome de usuário informado.
	 * 
	 * <p>Este método delega a exclusão ao caso de uso {@link DeleteUserByUsernameUseCase}.</p>
	 *
	 * @param username o nome de usuário do usuário a ser removido
	 */
	@Transactional
	public void deleteByUsername(String username) {
		this.deleteUserByUsernameUseCase.execute(username);
	}

}
