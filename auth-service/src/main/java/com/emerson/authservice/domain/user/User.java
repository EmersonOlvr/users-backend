package com.emerson.authservice.domain.user;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emerson.authservice.domain.UUIDEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_data")
@DynamicInsert
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends UUIDEntity implements UserDetails {
	
	private static final long serialVersionUID = -1690235458815248664L;
	
	@JsonIgnore
	@Column(nullable = false, columnDefinition = "boolean not null default true")
	private Boolean active;

	@Column(length = 320, nullable = false)
	private String username;
	
	@JsonIgnore
	@Column(length = 255, nullable = false)
	private String password;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant createdAt;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "user_roles", // nome da tabela de junção
		joinColumns = @JoinColumn(name = "user_id"), // FK do lado do usuário
		inverseJoinColumns = @JoinColumn(name = "role_id") // FK do lado da role
	)
	private List<Role> roles;
	
	public List<String> getRoles() {
		return this.roles.stream().map(Role::getName).toList();
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).toList();
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return this.active;
	}

}
