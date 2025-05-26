package com.emerson.authservice.service.user.update.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record UpdateRolesRequest(
		@NotNull(message = "Informe as novas permissões") List<String> roles
) {

}
