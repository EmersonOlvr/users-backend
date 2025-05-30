package com.emerson.gatewayservice.client.auth;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emerson.gatewayservice.client.auth.dto.AuthUserDto;
import com.emerson.gatewayservice.client.auth.dto.CreateAuthUserRequest;
import com.emerson.gatewayservice.client.auth.dto.UpdatePasswordRequest;
import com.emerson.gatewayservice.client.auth.dto.UpdateRolesRequest;
import com.emerson.gatewayservice.client.auth.dto.UpdateUsernameRequest;

/**
 * Esta interface define o cliente Feign responsável por interagir com os recursos de usuário
 * do `auth-service`, no endpoint `/v1/user`.
 *
 * <p>Permite operações de CRUD e gerenciamento de credenciais e roles dos usuários autenticados.</p>
 */
@FeignClient(
	name = "auth-service-user", 
	url = "${auth-service.url}/v1/user"
)
public interface AuthUserClient {
	
	@GetMapping("/{id}")
	AuthUserDto get(@PathVariable String id);
	
	@GetMapping("/{id}/roles")
	List<String> getRolesById(@PathVariable String id);
	
	@PostMapping("/register")
	AuthUserDto register(@RequestBody CreateAuthUserRequest userDto);
	
	@PutMapping("/{id}/username")
	void updateUsername(@PathVariable String id, @RequestBody UpdateUsernameRequest usernameRequest);
	
	@PutMapping("/{id}/password")
	void updatePassword(@PathVariable String id, @RequestBody UpdatePasswordRequest passwordRequest);
	
	@PutMapping("/{id}/roles")
	void updateRoles(@PathVariable String id, @RequestBody UpdateRolesRequest rolesRequest);
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable String id);
	
}
