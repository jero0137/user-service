package com.emazon.user_service.domain.useCase;

import com.emazon.user_service.domain.api.IUserService;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUseCase implements IUserService {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final PasswordEncoder passwordEncoder;


    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void registerUser(User user) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userPersistencePort.existsByDocument(user.getDocument())) {
            throw new IllegalArgumentException("Document already exists");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        Role role = rolePersistencePort.findByName("AUX_BODEGA");
        user.setRole(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userPersistencePort.registerUser(user);
    }
}
