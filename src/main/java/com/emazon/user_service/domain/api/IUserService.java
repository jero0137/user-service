package com.emazon.user_service.domain.api;

import com.emazon.user_service.domain.model.User;

public interface IUserService {
    void registerAuxBodega(User user);
    void registerUserClient(User user);
}
