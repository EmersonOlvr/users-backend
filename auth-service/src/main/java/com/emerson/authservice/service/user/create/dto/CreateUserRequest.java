package com.emerson.authservice.service.user.create.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
		@NotBlank(message = "Informe um e-mail/usuário") String username,
		@NotBlank(message = "Informe a senha") String password,
		@NotBlank(message = "Repita a senha") String passwordRepeat
) {

}
