package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.AuthDtoResponse;
import com.emazon.user_service.application.dto.AuthDtoRequest;
import com.emazon.user_service.application.mapper.AuthDtoMapper;
import com.emazon.user_service.application.mapper.LoginDtoMapper;
import com.emazon.user_service.domain.api.IAutheticationService;
import com.emazon.user_service.domain.model.Login;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationHandler implements  IAuthenticationHandler{

    private final IAutheticationService autheticationService;

    private final LoginDtoMapper loginDtoMapper;
    private final AuthDtoMapper authDtoMapper;

    @Override
    public AuthDtoResponse login(AuthDtoRequest authDtoRequest) {

        Login login = loginDtoMapper.toLogin(authDtoRequest);
        return authDtoMapper.toAuthDtoResponse(autheticationService.login(login));
    }
}
