package com.emazon.user_service.infrastructure.configuration;

import com.emazon.user_service.domain.api.IAutheticationService;
import com.emazon.user_service.domain.api.IUserService;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.domain.useCase.AuthUseCase;
import com.emazon.user_service.domain.useCase.UserUseCase;
import com.emazon.user_service.infrastructure.configuration.Security.JwtConfig.JwtAuthenticationFilter;
import com.emazon.user_service.infrastructure.configuration.Security.JwtConfig.JwtTokenProvider;
import com.emazon.user_service.infrastructure.output.adapter.RoleJpaAdapter;
import com.emazon.user_service.infrastructure.output.adapter.SecurityJpaAdapter;
import com.emazon.user_service.infrastructure.output.adapter.UserJpaAdapter;
import com.emazon.user_service.infrastructure.output.mapper.RoleEntityMapper;
import com.emazon.user_service.infrastructure.output.mapper.UserEntityMapper;
import com.emazon.user_service.infrastructure.output.repository.IRoleRepository;
import com.emazon.user_service.infrastructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final JwtTokenProvider jwtTokenProvider;


    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    IUserService userService() {
        return new UserUseCase(userPersistencePort(), rolePersistencePort(), passwordEncoder());
    }

    @Bean
    public ISecurityPersistencePort securityPersistencePort() throws Exception {
        return new SecurityJpaAdapter(jwtTokenProvider, authenticationManager(authenticationConfiguration), userEntityMapper);
    }

    @Bean
    IAutheticationService autheticationService() throws Exception {
        return new AuthUseCase(userPersistencePort(), securityPersistencePort());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService());
    }


}

