package com.emazon.user_service.application.handler;

import com.emazon.user_service.Utils.Constats;
import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.application.mapper.UserDtoMapper;
import com.emazon.user_service.domain.api.IUserService;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserService userService;
    private final UserDtoMapper userDtoMapper;

    @Override
    public void registerUser(RegisterDtoRequest userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("UserDto is null");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setDocument(userDto.getDocument());
        user.setPhone(userDto.getPhone());
        user.setBirthDate(userDto.getBirthDate());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); // Asegúrate de encriptar la contraseña en el UseCase o en el servicio correspondiente

        userService.registerUser(user);
    }
}
