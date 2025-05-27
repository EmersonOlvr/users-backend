package com.emerson.userinfoservice.infrastructure.gateways;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.domain.model.User;
import com.emerson.userinfoservice.infrastructure.persistence.entity.JpaUserInfo;
import com.emerson.userinfoservice.infrastructure.persistence.mapper.UserMapper;
import com.emerson.userinfoservice.infrastructure.persistence.repository.JpaUserInfoRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserInfoRepositoryGatewayImpl implements UserInfoRepositoryGateway {
	
	private final JpaUserInfoRepository repository;
	private final UserMapper entityMapper;
	
	@Override
	public User save(User user) {
		JpaUserInfo userEntity = this.entityMapper.toJpa(user);
		return this.entityMapper.toDomain(this.repository.save(userEntity));
	}

	@Override
	public boolean existsById(String id) {
		return this.repository.existsById(id);
	}

	@Override
	public boolean existsByCpf(String cpf) {
		return this.repository.existsByCpf(cpf);
	}

	@Override
	public Optional<User> findById(String id) {
		return this.entityMapper.toDomain(this.repository.findById(id));
	}

	@Override
	public void deleteById(String id) {
		this.repository.deleteById(id);
	}

}
