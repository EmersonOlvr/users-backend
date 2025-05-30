package com.emerson.gatewayservice.service.user;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.emerson.gatewayservice.client.auth.AuthUserClient;
import com.emerson.gatewayservice.client.auth.dto.AuthUserDto;
import com.emerson.gatewayservice.client.auth.dto.CreateAuthUserRequest;
import com.emerson.gatewayservice.client.auth.dto.UpdatePasswordRequest;
import com.emerson.gatewayservice.client.auth.dto.UpdateRolesRequest;
import com.emerson.gatewayservice.client.auth.dto.UpdateUsernameRequest;
import com.emerson.gatewayservice.client.userinfo.UserInfoClient;
import com.emerson.gatewayservice.client.userinfo.dto.UpdatePersonalInfoRequest;
import com.emerson.gatewayservice.client.userinfo.dto.UserInfoDto;
import com.emerson.gatewayservice.service.user.dto.CreateUserRequest;
import com.emerson.gatewayservice.service.user.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Esta classe é uma fachada responsável por coordenar operações relacionadas ao
 * gerenciamento de usuários, integrando dados entre o serviço de autenticação
 * ({@link AuthUserClient}) e o serviço de informações pessoais
 * ({@link UserInfoClient}).
 *
 * <p>É a principal camada de orquestração para criação, atualização, leitura e
 * exclusão de usuários no gateway.</p>
 */
@Service
@Slf4j
public class UserFacade {

	private final AuthUserClient authUserClient;
	private final UserInfoClient userInfoClient;

	public UserFacade(AuthUserClient authUserClient, UserInfoClient userInfoClient) {
		this.authUserClient = authUserClient;
		this.userInfoClient = userInfoClient;
	}

	/**
	 * Obtém os dados completos de um usuário (dados de autenticação e informações
	 * pessoais) a partir do seu ID.
	 *
	 * @param id ID do usuário
	 * @return DTO unificado com dados do auth-service e user-info-service
	 */
	public UserDto getById(String id) {
		AuthUserDto authUser = this.authUserClient.get(id);
		UserInfoDto userInfo = this.userInfoClient.get(id);
		
		return new UserDto(authUser, userInfo);
	}

	/**
	 * Obtém as roles do usuário a partir do auth-service e as armazena no cache
	 * local.
	 *
	 * @param userId ID do usuário
	 * @return lista de roles associadas ao usuário
	 */
	@Cacheable(value = "userRoles", key = "#userId")
	public List<String> getRolesById(String userId) {
		log.info("getRolesById( \"" + userId + "\" )");
		
		return this.authUserClient.getRolesById(userId);
	}

	/**
	 * Cria um novo usuário, realizando o registro tanto no auth-service quanto no
	 * user-info-service.
	 *
	 * @param request objeto com os dados necessários para criação do usuário
	 * @return DTO unificado representando o novo usuário
	 */
	public UserDto create(CreateUserRequest request) {
		// cadastra o login
		CreateAuthUserRequest createAuthUserRequest = new CreateAuthUserRequest(
				request.username(), 
				request.password(),
				request.passwordRepeat()
		);
		AuthUserDto authUser = this.authUserClient.register(createAuthUserRequest);
		
		// cadastra as informações pessoais do usuário (nome, cpf, etc)
		UserInfoDto createUserInfoRequest = new UserInfoDto(
				authUser.id(), 
				request.fullName(), 
				request.cpf()
		);
		UserInfoDto userInfo = this.userInfoClient.register(createUserInfoRequest);
		
		return new UserDto(authUser, userInfo);
	}

	/**
	 * Atualiza o nome de usuário (username) no auth-service.
	 *
	 * @param id                    ID do usuário
	 * @param updateUsernameRequest dados para atualização do username
	 * @return DTO atualizado do usuário
	 */
	public UserDto updateUsername(String id, UpdateUsernameRequest updateUsernameRequest) {
		this.authUserClient.updateUsername(id, updateUsernameRequest);
		
		return this.getById(id);
	}

	/**
	 * Atualiza as roles de um usuário no auth-service.
	 *
	 * @param id                 ID do usuário
	 * @param updateRolesRequest nova lista de roles
	 * @return DTO atualizado do usuário
	 */
	public UserDto updateRoles(String id, UpdateRolesRequest updateRolesRequest) {
		this.authUserClient.updateRoles(id, updateRolesRequest);
		
		return this.getById(id);
	}

	/**
	 * Atualiza a senha de um usuário no auth-service.
	 *
	 * @param id                    ID do usuário
	 * @param updatePasswordRequest dados para atualização da senha
	 * @return DTO atualizado do usuário
	 */
	public UserDto updatePassword(String id, UpdatePasswordRequest updatePasswordRequest) {
		this.authUserClient.updatePassword(id, updatePasswordRequest);
		
		return this.getById(id);
	}

	/**
	 * Atualiza as informações pessoais do usuário (nome e CPF) no
	 * user-info-service.
	 *
	 * @param id                  ID do usuário
	 * @param personalInfoRequest dados atualizados de nome e CPF
	 * @return DTO atualizado do usuário
	 */
	public UserDto updatePersonalInfo(@PathVariable String id, @RequestBody UpdatePersonalInfoRequest personalInfoRequest) {
		UserInfoDto userInfo = this.userInfoClient.updatePersonalInfo(id, personalInfoRequest);
		AuthUserDto authUser = this.authUserClient.get(id);
		
		return new UserDto(authUser, userInfo);
	}

	/**
	 * Exclui o usuário dos dois serviços: auth-service e user-info-service.
	 *
	 * @param id ID do usuário a ser excluído
	 */
	public void deleteById(String id) {
		this.authUserClient.delete(id);
		this.userInfoClient.delete(id);
	}

}
