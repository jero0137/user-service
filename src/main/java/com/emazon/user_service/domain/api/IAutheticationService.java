package com.emazon.user_service.domain.api;

import com.emazon.user_service.domain.model.Authentication;
import com.emazon.user_service.domain.model.Login;

public interface IAutheticationService {
    Authentication login(Login login);
}
