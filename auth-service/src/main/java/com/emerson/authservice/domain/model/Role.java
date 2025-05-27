package com.emerson.authservice.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {

	private String id;
	private String name;
	
	public enum Values {
		USER, 
		ADMIN
	}
	
	public Role(String name) {
		this.name = name;
	}

}
