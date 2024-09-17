package com.emazon.user_service.application.mapper;

import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    User toUser(RegisterDtoRequest registerDtoRequest);
}
