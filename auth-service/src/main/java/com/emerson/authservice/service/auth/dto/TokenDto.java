package com.emerson.authservice.service.auth.dto;

import java.time.Instant;

public record TokenDto(
		String token,
		Instant expiration
) {

}
