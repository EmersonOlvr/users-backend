package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.PasswordServiceGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.BadRequestException;
import com.emerson.authservice.domain.model.User;

/**
 * Caso de uso responsável por atualizar a senha de um usuário.
 *
 * <p>Valida a senha atual, garante que a nova senha foi confirmada corretamente
 * e persiste a nova senha codificada no banco de dados.</p>
 */
public class UpdateUserPasswordUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	private final FindUserByIdUseCase findUserByIdUseCase;
	private final PasswordServiceGateway passwordServiceGateway;
	
	public UpdateUserPasswordUseCase(
			UserRepositoryGateway userRepositoryGateway, 
			FindUserByIdUseCase findUserByIdUseCase, 
			PasswordServiceGateway passwordServiceGateway) 
	{
		this.userRepositoryGateway = userRepositoryGateway;
		this.findUserByIdUseCase = findUserByIdUseCase;
		this.passwordServiceGateway = passwordServiceGateway;
	}

	/**
	 * Executa a atualização da senha de um usuário.
	 *
	 * @param id                Identificador do usuário.
	 * @param currentPassword   Senha atual informada pelo usuário (pode ser nula).
	 * @param newPassword       Nova senha desejada.
	 * @param newPasswordRepeat Confirmação da nova senha.
	 * @return O usuário com a senha atualizada.
	 *
	 * @throws BadRequestException Se a senha atual estiver incorreta ou se as novas
	 *                             senhas não forem iguais.
	 */
	public User execute(String id, String currentPassword, String newPassword, String newPasswordRepeat) {
		User user = this.findUserByIdUseCase.execute(id);
		
		if (currentPassword == null || !this.passwordServiceGateway.matches(currentPassword, user.getPassword()))
			throw new BadRequestException("A senha atual informada está incorreta.");
		
		if (!newPassword.equals(newPasswordRepeat))
			throw new BadRequestException("As novas senhas informadas não são iguais.");
		
		String hashedPassword = this.passwordServiceGateway.encode(newPassword);
		user.setPassword(hashedPassword);
		this.userRepositoryGateway.save(user);
		
		return user;
	}

}
