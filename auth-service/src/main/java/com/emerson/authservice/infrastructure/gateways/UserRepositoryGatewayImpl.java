package com.emerson.authservice.infrastructure.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.model.Role;
import com.emerson.authservice.domain.model.User;
import com.emerson.authservice.infrastructure.persistence.entity.JpaUser;
import com.emerson.authservice.infrastructure.persistence.mapper.RoleMapper;
import com.emerson.authservice.infrastructure.persistence.mapper.UserMapper;
import com.emerson.authservice.infrastructure.persistence.repository.JpaUserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserRepositoryGatewayImpl implements UserRepositoryGateway {

	private final JpaUserRepository repository;
	private final UserMapper entityMapper;
	private final RoleMapper roleEntityMapper;

	@Override
	public User save(User user) {
		JpaUser userEntity = this.entityMapper.toJpa(user);
		return this.entityMapper.toDomain(this.repository.save(userEntity));
	}

	@Override
	public boolean existsByUsername(String username) {
		return this.repository.existsByUsername(username);
	}

	@Override
	public Optional<User> findById(String userId) {
		return this.entityMapper.toDomain(this.repository.findById(userId));
	}

	@Override
	public Optional<User> findByIdWithRoles(String id) {
		return this.entityMapper.toDomain(this.repository.findByIdWithRoles(id));
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return this.entityMapper.toDomain(this.repository.findByUsername(username));
	}

	@Override
	public List<Role> getRolesById(String id) {
		return this.roleEntityMapper.toDomain(this.repository.getRolesById(id));
	}

	@Override
	public void deleteByUsername(String username) {
		this.repository.deleteByUsername(username);
	}

	@Override
	public void deleteById(String id) {
		this.repository.deleteById(id);
	}

	@Override
	public long count() {
		return this.repository.count();
	}

}
