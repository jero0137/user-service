package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.domain.model.User;

public interface IUserHandler {
    void registerUser(RegisterDtoRequest user);
}
