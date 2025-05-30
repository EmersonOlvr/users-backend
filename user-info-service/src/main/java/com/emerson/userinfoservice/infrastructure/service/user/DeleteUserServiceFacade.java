package com.emerson.userinfoservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.application.usecase.DeleteUserByIdUseCase;

import lombok.AllArgsConstructor;

/**
 * Esta classe é uma fachada responsável por orquestrar o processo de exclusão de um usuário.
 *
 * <p>Ela utiliza o caso de uso {@link DeleteUserByIdUseCase} para executar a lógica de
 * exclusão.</p>
 */
@Service
@AllArgsConstructor
public class DeleteUserServiceFacade {
	
	private final DeleteUserByIdUseCase deleteUserByIdUseCase;

	/**
	 * Exclui o usuário com o ID fornecido.
	 *
	 * @param id identificador do usuário a ser excluído
	 */
	@Transactional
	public void deleteById(String id) {
		this.deleteUserByIdUseCase.execute(id);
	}

}
