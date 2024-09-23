package com.emazon.user_service.infrastructure.output.mapper;

import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.infrastructure.output.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toUserEntity(User user);
    User toUser(UserEntity userEntity);
}
