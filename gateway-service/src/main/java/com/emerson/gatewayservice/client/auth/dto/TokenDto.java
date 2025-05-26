package com.emerson.gatewayservice.client.auth.dto;

import java.time.Instant;

public record TokenDto(
		String token,
		Instant expiration
) {

}
