package com.emerson.authservice.application.usecase.auth;

import com.emerson.authservice.application.dto.TokenDto;
import com.emerson.authservice.application.gateways.JwtServiceGateway;
import com.emerson.authservice.application.gateways.PasswordServiceGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.ForbiddenException;
import com.emerson.authservice.domain.exception.InvalidCredentialsException;
import com.emerson.authservice.domain.model.User;

/**
 * Caso de uso responsável por autenticar um usuário com base no nome de usuário e senha.
 *
 * <p>Verifica se o usuário existe, está ativo e se a senha fornecida corresponde
 * à senha armazenada. Em caso de sucesso, retorna um token JWT válido.</p>
 *
 * <p>Este caso de uso encapsula toda a lógica de login e mantém o domínio
 * isolado de detalhes da infraestrutura como codificação de senhas e geração de tokens.</p>
 */
public class LoginWithUsernameAndPasswordUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	private final PasswordServiceGateway passwordServiceGateway;
	private final JwtServiceGateway jwtServiceGateway;

	public LoginWithUsernameAndPasswordUseCase(
			UserRepositoryGateway userRepositoryGateway, 
			PasswordServiceGateway passwordServiceGateway, 
			JwtServiceGateway jwtServiceGateway) 
	{
		this.userRepositoryGateway = userRepositoryGateway;
		this.passwordServiceGateway = passwordServiceGateway;
		this.jwtServiceGateway = jwtServiceGateway;
	}

	/**
	 * Executa o processo de autenticação de um usuário.
	 *
	 * @param username O nome de usuário fornecido.
	 * @param password A senha em texto puro fornecida.
	 * @return Um {@link TokenDto} contendo o token JWT e sua data de expiração.
	 *
	 * @throws InvalidCredentialsException Se o usuário não existir ou a senha estiver incorreta.
	 * @throws ForbiddenException Se o usuário estiver inativo.
	 */
	public TokenDto execute(String username, String password) {
		User user = this.userRepositoryGateway.findByUsername(username)
												.orElseThrow(InvalidCredentialsException::new);
		
		if (!Boolean.TRUE.equals(user.getActive()))
			throw new ForbiddenException("Esta conta foi desativada.");
		
		if (!this.passwordServiceGateway.matches(password, user.getPassword()))
			throw new InvalidCredentialsException();
		
		return this.jwtServiceGateway.generateToken(user);
	}

}
