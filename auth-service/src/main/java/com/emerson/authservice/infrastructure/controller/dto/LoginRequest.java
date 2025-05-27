package com.emerson.authservice.infrastructure.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		@NotBlank(message = "Informe um e-mail/usuário") String username,
		@NotBlank(message = "Informe a senha") String password
) {

}
