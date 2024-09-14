package com.emazon.user_service.application.mapper;

import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @Mapping(target = "role", ignore = true)
    User toUser(RegisterDtoRequest userDto);
}
