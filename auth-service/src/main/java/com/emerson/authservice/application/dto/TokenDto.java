package com.emerson.authservice.application.dto;

import java.time.Instant;

/**
 * DTO que representa um token JWT gerado após a autenticação do usuário.
 *
 * <p>Contém o token propriamente dito e a data/hora de expiração para controle de
 * validade.</p>
 *
 * @param token      O token JWT gerado, utilizado para autenticação nas requisições.
 * @param expiration A data e hora em que o token expira, em formato UTC.
 */
public record TokenDto(
		String token,
		Instant expiration
) {

}
