package com.emazon.user_service.UseCaseTests;

import com.emazon.user_service.domain.exception.UserNotFoundException;
import com.emazon.user_service.domain.model.Authentication;
import com.emazon.user_service.domain.model.Login;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.domain.useCase.AuthUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private ISecurityPersistencePort securityPersistencePort;

    @InjectMocks
    private AuthUseCase authUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginWithValidCredentialsReturnsAuthentication() {
        Login login = new Login("test@example.com", "password");
        User user = new User();
        Authentication authentication = new Authentication("token");

        when(userPersistencePort.findByEmail(login.getEmail())).thenReturn(Optional.of(user));
        doNothing().when(securityPersistencePort).autheticate(login);
        when(securityPersistencePort.getToken(user)).thenReturn(authentication);

        Authentication result = authUseCase.login(login);

        assertEquals(authentication, result);
    }

    @Test
    void loginWithInvalidEmailThrowsUserNotFoundException() {
        Login login = new Login("invalid@example.com", "password");

        when(userPersistencePort.findByEmail(login.getEmail())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authUseCase.login(login));
    }

    @Test
    void loginWithValidEmailAndInvalidPasswordThrowsException() {
        Login login = new Login("test@example.com", "wrongpassword");
        User user = new User();

        when(userPersistencePort.findByEmail(login.getEmail())).thenReturn(Optional.of(user));
        doThrow(new RuntimeException("Invalid credentials")).when(securityPersistencePort).autheticate(login);

        assertThrows(RuntimeException.class, () -> authUseCase.login(login));
    }
}
