package com.emerson.userinfoservice.domain.user;

import org.hibernate.annotations.DynamicInsert;

import com.emerson.userinfoservice.domain.UUIDEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "user_info")
@DynamicInsert
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo extends UUIDEntity {
	
	private static final long serialVersionUID = -2689830137531675652L;
	
	@JsonIgnore
	@Column(nullable = false, columnDefinition = "boolean not null default true")
	private Boolean active;
	
	@Column(length = 255, nullable = false)
	private String fullName;
	
	@Column(length = 11, nullable = false)
	private String cpf;

}
