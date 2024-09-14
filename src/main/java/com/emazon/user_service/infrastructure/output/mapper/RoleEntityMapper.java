package com.emazon.user_service.infrastructure.output.mapper;

import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.infrastructure.output.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    Role toRole(RoleEntity roleEntity);
}
