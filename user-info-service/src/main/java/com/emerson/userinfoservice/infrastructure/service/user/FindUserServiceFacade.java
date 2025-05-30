package com.emerson.userinfoservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.application.usecase.FindUserByIdUseCase;
import com.emerson.userinfoservice.domain.model.User;

import lombok.AllArgsConstructor;

/**
 * Esta classe é uma fachada responsável por orquestrar a busca de informações de um usuário.
 *
 * <p>Ela utiliza o caso de uso {@link FindUserByIdUseCase} para recuperar um usuário
 * com base no seu identificador.</p>
 */
@Service
@AllArgsConstructor
public class FindUserServiceFacade {
	
	private final FindUserByIdUseCase findUserByIdUseCase;

	/**
	 * Busca e retorna o usuário com o ID fornecido.
	 *
	 * @param id identificador do usuário
	 * @return o usuário correspondente
	 */
	@Transactional
	public User getById(String id) {
		return this.findUserByIdUseCase.execute(id);
	}
	
}
