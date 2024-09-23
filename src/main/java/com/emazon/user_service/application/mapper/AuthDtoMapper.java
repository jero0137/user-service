package com.emazon.user_service.application.mapper;

import com.emazon.user_service.application.dto.AuthDtoResponse;
import com.emazon.user_service.domain.model.Authentication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthDtoMapper {
    AuthDtoResponse toAuthDtoResponse(Authentication authentication);
}
