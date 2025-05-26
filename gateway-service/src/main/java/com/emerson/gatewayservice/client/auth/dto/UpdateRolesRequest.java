package com.emerson.gatewayservice.client.auth.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record UpdateRolesRequest(
		@NotNull(message = "Informe as novas permissões") List<String> roles
) {

}
