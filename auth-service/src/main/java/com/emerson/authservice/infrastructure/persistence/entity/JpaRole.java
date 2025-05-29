package com.emerson.authservice.infrastructure.persistence.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "role")
@DynamicInsert
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JpaRole extends UUIDEntity {

	private static final long serialVersionUID = -6879283114471720329L;
	
	@Column(nullable = false, unique = true)
	private String name;

}
