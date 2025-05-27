package com.emerson.authservice.infrastructure.gateways;

import java.util.List;
import java.util.Optional;

import com.emerson.authservice.application.gateways.RoleRepositoryGateway;
import com.emerson.authservice.domain.model.Role;
import com.emerson.authservice.infrastructure.persistence.entity.JpaRole;
import com.emerson.authservice.infrastructure.persistence.mapper.RoleMapper;
import com.emerson.authservice.infrastructure.persistence.repository.JpaRoleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleRepositoryGatewayImpl implements RoleRepositoryGateway {
	
	private final JpaRoleRepository repository;
	private final RoleMapper entityMapper;

	@Override
	public Role save(Role role) {
		JpaRole roleEntity = this.entityMapper.toJpa(role);
		return this.entityMapper.toDomain(this.repository.save(roleEntity));
	}

	@Override
	public List<Role> saveAll(List<Role> newRoles) {
		List<JpaRole> roleEntities = this.entityMapper.toJpa(newRoles);
		return this.entityMapper.toDomain(this.repository.saveAll(roleEntities));
	}

	@Override
	public List<Role> findByNameIn(List<String> names) {
		return this.entityMapper.toDomain(this.repository.findByNameIn(names));
	}

	@Override
	public Optional<Role> findByName(String name) {
		return this.entityMapper.toDomain(this.repository.findByName(name));
	}

}
