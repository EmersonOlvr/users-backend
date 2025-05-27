package com.emerson.userinfoservice.infrastructure.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePersonalInfoRequest(
		@NotBlank(message = "Informe o nome completo") String fullName,
		@NotBlank(message = "Informe o CPF") String cpf
) {

}
