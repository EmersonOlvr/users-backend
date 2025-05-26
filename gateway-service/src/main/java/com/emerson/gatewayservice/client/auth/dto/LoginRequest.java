package com.emerson.gatewayservice.client.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		@NotBlank(message = "Informe um e-mail/usuário") String username,
		@NotBlank(message = "Informe a senha") String password
) {

}
