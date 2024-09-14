package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.User;

public interface IUserPersistencePort {
    void registerUser(User user);
    boolean existsByEmail(String email);
    boolean existsByDocument(String document);
}
