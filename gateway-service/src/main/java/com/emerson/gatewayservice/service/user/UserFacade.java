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

@Service
@Slf4j
public class UserFacade {

	private final AuthUserClient authUserClient;
	private final UserInfoClient userInfoClient;

	public UserFacade(AuthUserClient authUserClient, UserInfoClient userInfoClient) {
		this.authUserClient = authUserClient;
		this.userInfoClient = userInfoClient;
	}
	
	public UserDto getById(String id) {
		AuthUserDto authUser = this.authUserClient.get(id);
		UserInfoDto userInfo = this.userInfoClient.get(id);
		
		return new UserDto(authUser, userInfo);
	}
	
	@Cacheable(value = "userRoles", key = "#userId")
	public List<String> getRolesById(String userId) {
		log.info("getRolesById( \"" + userId + "\" )");
		
		return this.authUserClient.getRolesById(userId);
	}
	
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
	
	public UserDto updateUsername(String id, UpdateUsernameRequest updateUsernameRequest) {
		this.authUserClient.updateUsername(id, updateUsernameRequest);
		
		return this.getById(id);
	}
	
	public UserDto updateRoles(String id, UpdateRolesRequest updateRolesRequest) {
		this.authUserClient.updateRoles(id, updateRolesRequest);
		
		return this.getById(id);
	}
	
	public UserDto updatePassword(String id, UpdatePasswordRequest updatePasswordRequest) {
		this.authUserClient.updatePassword(id, updatePasswordRequest);
		
		return this.getById(id);
	}
	
	public UserDto updatePersonalInfo(@PathVariable String id, @RequestBody UpdatePersonalInfoRequest personalInfoRequest) {
		UserInfoDto userInfo = this.userInfoClient.updatePersonalInfo(id, personalInfoRequest);
		AuthUserDto authUser = this.authUserClient.get(id);
		
		return new UserDto(authUser, userInfo);
	}
	
	public void deleteById(String id) {
		this.authUserClient.delete(id);
		this.userInfoClient.delete(id);
	}

}
