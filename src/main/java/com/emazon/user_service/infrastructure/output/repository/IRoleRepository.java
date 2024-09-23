package com.emazon.user_service.infrastructure.output.repository;

import com.emazon.user_service.infrastructure.output.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
