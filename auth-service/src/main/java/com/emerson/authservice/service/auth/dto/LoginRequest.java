package com.emerson.authservice.service.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		@NotBlank(message = "Informe um e-mail/usuário") String username,
		@NotBlank(message = "Informe a senha") String password
) {

}
