package com.emazon.user_service.infrastructure.output.adapter;

import com.emazon.user_service.domain.model.Authentication;
import com.emazon.user_service.domain.model.Login;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.infrastructure.configuration.Security.JwtConfig.JwtTokenProvider;
import com.emazon.user_service.infrastructure.exception.InvalidCredentialsException;
import com.emazon.user_service.infrastructure.output.entity.UserEntity;
import com.emazon.user_service.infrastructure.output.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@RequiredArgsConstructor
public class SecurityJpaAdapter implements ISecurityPersistencePort {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void autheticate(Login login) {
        try{

                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
                );
        }catch (Exception e){
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public Authentication getToken(User user) {
        UserEntity securityUser = userEntityMapper.toUserEntity(user);
        return new Authentication(jwtTokenProvider.getToken(securityUser));
    }
}
