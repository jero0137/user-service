package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.RegisterDtoRequest;

public interface IUserHandler {
    void registerAuxBodega(RegisterDtoRequest user);
    void registerUserClient(RegisterDtoRequest user);
}
