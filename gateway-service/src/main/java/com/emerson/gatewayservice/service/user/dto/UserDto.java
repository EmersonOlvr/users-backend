package com.emerson.gatewayservice.service.user.dto;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.emerson.gatewayservice.client.auth.dto.AuthUserDto;
import com.emerson.gatewayservice.client.userinfo.dto.UserInfoDto;

import lombok.Data;

@Data
public class UserDto {

	private String id;
	private Instant createdAt;
	private String username;
	private List<String> roles;
	
	private String fullName;
	private String cpf;

	public UserDto(AuthUserDto authUserDto, UserInfoDto userInfoDto) {
		BeanUtils.copyProperties(authUserDto, this);
		BeanUtils.copyProperties(userInfoDto, this);
	}

}
