package com.emazon.user_service.application.mapper;

import com.emazon.user_service.application.dto.AuthDtoRequest;
import com.emazon.user_service.domain.model.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginDtoMapper {
    Login toLogin(AuthDtoRequest authDtoRequest);
}
