package com.emerson.authservice.domain.model;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {
	
	private String id;
	
	@JsonIgnore
	private Boolean active;
	
	private String username;

	@JsonIgnore
	private String password;
	
	private Instant createdAt;

	@JsonIgnore
	private List<Role> roles;
	
	@JsonProperty("roles")
	public List<String> getRolesAsString() {
		if (this.roles == null) return List.of();
		return this.roles.stream().map(Role::getName).toList();
	}
	
}
