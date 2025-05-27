package com.emerson.authservice.infrastructure.persistence.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.emerson.authservice.domain.model.Role;
import com.emerson.authservice.infrastructure.persistence.entity.JpaRole;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	
    Role toDomain(JpaRole jpaEntity);
    List<Role> toDomain(List<JpaRole> jpaEntities);
    
    JpaRole toJpa(Role domainRole);
    List<JpaRole> toJpa(List<Role> domainRoles);

    default Optional<Role> toDomain(Optional<JpaRole> jpaEntity) {
        return jpaEntity.map(this::toDomain);
    }

    default Optional<JpaRole> toJpa(Optional<Role> domainRole) {
        return domainRole.map(this::toJpa);
    }
    
}