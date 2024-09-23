package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.Authentication;
import com.emazon.user_service.domain.model.Login;
import com.emazon.user_service.domain.model.User;

public interface ISecurityPersistencePort {
    void autheticate(Login login);
    Authentication getToken(User user);
}
