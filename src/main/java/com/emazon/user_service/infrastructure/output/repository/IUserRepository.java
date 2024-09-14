package com.emazon.user_service.infrastructure.output.repository;

import com.emazon.user_service.infrastructure.output.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByDocument(String document);
    Optional<UserEntity> findByEmail(String email);
}
