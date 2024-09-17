package com.emazon.user_service.infrastructure.output.adapter;

import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.infrastructure.exception.EmailAlreadyExistsException;
import com.emazon.user_service.infrastructure.output.entity.RoleEntity;
import com.emazon.user_service.infrastructure.output.entity.UserEntity;
import com.emazon.user_service.infrastructure.output.mapper.UserEntityMapper;
import com.emazon.user_service.infrastructure.output.repository.IUserRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Override
    public void registerUser(User user) {

        UserEntity userEntity = userEntityMapper.toUserEntity(user);

        if(userRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyExistsException();
        }
        if(userRepository.existsByDocument(user.getDocument())){
            throw new EmailAlreadyExistsException();
        }
        userRepository.save(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocument(String document) {
        return userRepository.existsByDocument(document);
    }
}
