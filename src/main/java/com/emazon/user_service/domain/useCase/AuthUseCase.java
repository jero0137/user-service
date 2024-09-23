package com.emazon.user_service.domain.useCase;

import com.emazon.user_service.domain.api.IAutheticationService;
import com.emazon.user_service.domain.exception.UserNotFoundException;
import com.emazon.user_service.domain.model.Authentication;
import com.emazon.user_service.domain.model.Login;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;

import java.util.Optional;


public class AuthUseCase implements IAutheticationService {

    IUserPersistencePort userPersistencePort;
    ISecurityPersistencePort securityPersistencePort;

    public AuthUseCase(IUserPersistencePort userPersistencePort, ISecurityPersistencePort securityPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public Authentication login(Login login) {

        Optional<User> user = userPersistencePort.findByEmail(login.getEmail());

        if(user.isEmpty()) throw new UserNotFoundException();

        securityPersistencePort.autheticate(login);

        return securityPersistencePort.getToken(user.get());
    }
}
