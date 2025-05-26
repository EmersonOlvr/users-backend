package com.emerson.gatewayservice.service.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
		@NotBlank(message = "Informe um e-mail/usuário") String username,
		@NotBlank(message = "Informe a senha") String password,
		@NotBlank(message = "Repita a senha") String passwordRepeat,
		@NotBlank(message = "Informe o nome completo") String fullName,
		@NotBlank(message = "Informe o cpf") String cpf
) {

}
