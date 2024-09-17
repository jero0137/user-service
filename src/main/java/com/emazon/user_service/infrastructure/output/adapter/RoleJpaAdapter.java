package com.emazon.user_service.infrastructure.output.adapter;

import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.infrastructure.output.entity.RoleEntity;
import com.emazon.user_service.infrastructure.output.mapper.RoleEntityMapper;
import com.emazon.user_service.infrastructure.output.repository.IRoleRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role findByName(String roleName) {
        RoleEntity roleEntity = roleRepository.findByName(roleName);
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        return role;
    }
}
