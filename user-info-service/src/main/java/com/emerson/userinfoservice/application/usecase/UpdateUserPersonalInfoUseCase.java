package com.emerson.userinfoservice.application.usecase;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.domain.model.User;

/**
 * Caso de uso responsável por atualizar informações pessoais (nome e CPF) de um {@link User}.
 */
public class UpdateUserPersonalInfoUseCase {
	
	private final UserInfoRepositoryGateway userRepositoryGateway;
	private final FindUserByIdUseCase findUserByIdUseCase;
	
	public UpdateUserPersonalInfoUseCase(UserInfoRepositoryGateway userRepositoryGateway, FindUserByIdUseCase findUserByIdUseCase) {
		this.userRepositoryGateway = userRepositoryGateway;
		this.findUserByIdUseCase = findUserByIdUseCase;
	}

	/**
	 * Executa a atualização do nome completo e do CPF de um usuário.
	 * 
	 * @param id o identificador único do usuário.
	 * @param fullName o novo nome completo.
	 * @param cpf o novo CPF.
	 * @return o {@link User} com as informações atualizadas.
	 */
	public User execute(String id, String fullName, String cpf) {
		User user = this.findUserByIdUseCase.execute(id);
		
		user.setFullName(fullName);
		user.setCpf(cpf);
		this.userRepositoryGateway.save(user);
		
		return user;
	}

}
