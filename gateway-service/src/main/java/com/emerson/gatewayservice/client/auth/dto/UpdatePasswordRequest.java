package com.emerson.gatewayservice.client.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequest(
		@NotBlank(message = "Informe a senha atual") String currentPassword,
		@NotBlank(message = "Informe a nova senha") String newPassword,
		@NotBlank(message = "Repita a nova senha") String newPasswordRepeat
) {

}
