package com.emazon.user_service.infrastructure.output.adapter;

import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
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
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(user.getEmail());
        userEntity.setDocument(user.getDocument());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhone(user.getPhone());

        Role role = user.getRole();
        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId(role.getId());
        roleEntity.setName(role.getName());

        userEntity.setRole(roleEntity);
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setId(user.getId());

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
