package com.emerson.userinfoservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private String id;

	@JsonIgnore
	private Boolean active;

	private String fullName;

	private String cpf;

}
