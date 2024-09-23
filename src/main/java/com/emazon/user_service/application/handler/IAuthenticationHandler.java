package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.AuthDtoResponse;
import com.emazon.user_service.application.dto.AuthDtoRequest;

public interface IAuthenticationHandler {
    AuthDtoResponse login (AuthDtoRequest authDtoRequest);
}
