package com.emerson.authservice.infrastructure.persistence.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.emerson.authservice.domain.model.User;
import com.emerson.authservice.infrastructure.persistence.entity.JpaUser;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
	
    User toDomain(JpaUser jpaEntity);
    List<User> toDomain(List<JpaUser> jpaEntities);
    
    JpaUser toJpa(User domainUser);
    List<JpaUser> toJpa(List<User> domainUsers);

    default Optional<User> toDomain(Optional<JpaUser> jpaEntity) {
        return jpaEntity.map(this::toDomain);
    }

    default Optional<JpaUser> toJpa(Optional<User> domainRole) {
        return domainRole.map(this::toJpa);
    }
    
}