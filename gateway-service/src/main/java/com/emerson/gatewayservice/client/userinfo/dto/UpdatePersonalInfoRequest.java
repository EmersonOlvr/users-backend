package com.emerson.gatewayservice.client.userinfo.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePersonalInfoRequest(
		@NotBlank(message = "Informe o nome completo") String fullName,
		@NotBlank(message = "Informe o CPF") String cpf
) {

}
