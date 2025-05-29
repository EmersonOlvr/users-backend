package com.emerson.authservice.domain.model;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Representa um usuário do sistema.
 * 
 * <p>Contém informações básicas como identificador, nome de usuário, senha codificada (hash), 
 * estado de ativação, data de criação e papéis associados.</p>
 * 
 * <p>Campos sensíveis ou irrelevantes para a serialização JSON, como senha, estado ativo e lista
 * de papéis, são ignorados para proteger dados e evitar exposição desnecessária.</p>
 * 
 * <p>Fornece um método customizado para expor os nomes dos papéis em formato de lista de strings
 * para a serialização JSON.</p>
 */
@Data
public class User {

	/** Identificador único do usuário. */
	private String id;

	/** Indica se o usuário está ativo ou não. */
	@JsonIgnore
	private Boolean active;

	/** Nome de usuário ou e-mail utilizado para autenticação. */
	private String username;

	/** Senha criptografada do usuário (não exposta via JSON). */
	@JsonIgnore
	private String password;

	/** Data e hora da criação do usuário. */
	private Instant createdAt;

	/** Lista de papéis (roles) associados ao usuário (não exposta diretamente). */
	@JsonIgnore
	private List<Role> roles;

	/**
	 * Retorna os nomes dos papéis do usuário para serialização JSON.
	 * 
	 * @return lista dos nomes dos papéis associados ao usuário; lista vazia se não
	 *         houver papéis
	 */
	@JsonProperty("roles")
	public List<String> getRolesAsString() {
		if (this.roles == null) return List.of();
		return this.roles.stream().map(Role::getName).toList();
	}
	
}
