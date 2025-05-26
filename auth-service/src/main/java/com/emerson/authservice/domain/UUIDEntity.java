package com.emerson.authservice.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UUIDEntity implements Serializable {

	private static final long serialVersionUID = -1731436118796711533L;
	
	@Id
	protected String id;
	
	@PrePersist
	private void ensureUuid() {
		if (id == null) {
			id = UUID.randomUUID().toString();
		}
	}

}
