package com.emerson.gatewayservice.client.auth.dto;

import java.time.Instant;
import java.util.List;

public record AuthUserDto(
		String id,
		Instant createdAt,
		String username,
		List<String> roles
) {

}
