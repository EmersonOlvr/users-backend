package com.emerson.userinfoservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.application.usecase.UpdateUserPersonalInfoUseCase;
import com.emerson.userinfoservice.domain.model.User;
import com.emerson.userinfoservice.infrastructure.controller.dto.UpdatePersonalInfoRequest;

import lombok.AllArgsConstructor;

/**
 * Esta classe é uma fachada responsável por orquestrar a atualização das
 * informações pessoais de um usuário.
 *
 * <p>Ela utiliza o caso de uso {@link UpdateUserPersonalInfoUseCase} para aplicar
 * as alteraçõe.</p>
 */
@Service
@AllArgsConstructor
public class UpdateUserServiceFacade {
	
	private final UpdateUserPersonalInfoUseCase updateUserPersonalInfoUseCase;

	/**
	 * Atualiza as informações pessoais (nome completo e CPF) do usuário
	 * identificado pelo ID fornecido.
	 *
	 * @param id                  identificador do usuário
	 * @param personalInfoRequest objeto contendo os novos dados pessoais
	 * @return o usuário atualizado
	 */
	@Transactional
	public User updatePersonalInfo(String id, UpdatePersonalInfoRequest personalInfoRequest) {
		return this.updateUserPersonalInfoUseCase.execute(id, personalInfoRequest.fullName(), personalInfoRequest.cpf());
	}
	
}
