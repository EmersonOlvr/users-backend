package com.emerson.userinfoservice.infrastructure.persistence.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.emerson.userinfoservice.domain.model.User;
import com.emerson.userinfoservice.infrastructure.persistence.entity.JpaUserInfo;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	User toDomain(JpaUserInfo jpaEntity);
	List<User> toDomain(List<JpaUserInfo> jpaEntities);
	
	JpaUserInfo toJpa(User domainUser);
	List<JpaUserInfo> toJpa(List<User> domainUsers);

	default Optional<User> toDomain(Optional<JpaUserInfo> jpaEntity) {
		return jpaEntity.map(this::toDomain);
	}

	default Optional<JpaUserInfo> toJpa(Optional<User> domainRole) {
		return domainRole.map(this::toJpa);
	}
	
}