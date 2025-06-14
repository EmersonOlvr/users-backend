package com.emerson.gatewayservice.client.userinfo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emerson.gatewayservice.client.userinfo.dto.UpdatePersonalInfoRequest;
import com.emerson.gatewayservice.client.userinfo.dto.UserInfoDto;

/**
 * Esta interface define o cliente Feign responsável por interagir com os dados de informações
 * pessoais dos usuários no `user-info-service`, acessando o endpoint `/v1/user`.
 *
 * <p>Permite obter, criar, atualizar e deletar informações pessoais do usuário.</p>
 */
@FeignClient(
	name = "user-info-service-user", 
	url = "${user-info-service.url}/v1/user"
)
public interface UserInfoClient {
	
	@GetMapping("/{id}")
	UserInfoDto get(@PathVariable String id);

	@PostMapping
	UserInfoDto register(@RequestBody UserInfoDto userDto);

	@PutMapping("/{id}/personal-info")
	UserInfoDto updatePersonalInfo(@PathVariable String id, @RequestBody UpdatePersonalInfoRequest personalInfoRequest);

	@DeleteMapping("/{id}")
	void delete(@PathVariable String id);
	
}
