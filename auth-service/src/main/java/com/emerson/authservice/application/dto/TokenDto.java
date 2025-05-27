package com.emerson.authservice.application.dto;

import java.time.Instant;

public record TokenDto(
		String token,
		Instant expiration
) {

}
