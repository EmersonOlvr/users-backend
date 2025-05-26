package com.emerson.gatewayservice.client.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAuthUserRequest(
		@NotBlank(message = "Informe um e-mail/usuário") String username,
		@NotBlank(message = "Informe a senha") String password,
		@NotBlank(message = "Repita a senha") String passwordRepeat
) {

}
