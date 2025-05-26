package com.emerson.authservice.service.user.update.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUsernameRequest(
		@NotBlank(message = "Informe o novo e-mail/usuário") String username
) {

}
