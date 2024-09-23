package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void registerUser(User user);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByDocument(String document);
}
