package com.emerson.userinfoservice.service.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
		@NotBlank(message = "Informe o ID do usuário") String id,
		@NotBlank(message = "Informe o nome completo") String fullName,
		@NotBlank(message = "Informe o CPF") String cpf
) {

}
