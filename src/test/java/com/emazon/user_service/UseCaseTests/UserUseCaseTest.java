package com.emazon.user_service.UseCaseTests;

import com.emazon.user_service.Utils.Constats;
import com.emazon.user_service.Utils.RegexConstants;
import com.emazon.user_service.domain.exception.*;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.domain.useCase.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ShouldThrowUserNotNullException_WhenUserNameIsNull() {
        User user = new User();
        user.setName(null);
        user.setLastName("Doe");
        user.setDocument("123456789");
        user.setPhone("+573005698325");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setEmail("johndoe@example.com");
        user.setPassword("password");

        assertThrows(UserNotNullException.class, () -> userUseCase.registerUser(user));
    }

    @Test
    void registerUser_ShouldThrowPasswordNotNullException_WhenPasswordIsNull() {
        User user = new User();
        user.setName("John");
        user.setLastName("Doe");
        user.setDocument("123456789");
        user.setPhone("+573005698325");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setEmail("johndoe@example.com");
        user.setPassword(null);

        assertThrows(PasswordNotNullException.class, () -> userUseCase.registerUser(user));
    }

    @Test
    void registerUser_ShouldThrowIllegalPhoneFormatException_WhenPhoneIsInvalid() {
        User user = new User();
        user.setName("John");
        user.setLastName("Doe");
        user.setDocument("123456789");
        user.setPhone("invalid_phone");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setEmail("johndoe@example.com");
        user.setPassword("password");

        assertThrows(IllegalPhoneFormatException.class, () -> userUseCase.registerUser(user));
    }

    @Test
    void registerUser_ShouldRegisterUser_WhenUserIsValid() {
        User user = new User();
        user.setName("John");
        user.setLastName("Doe");
        user.setDocument("123456789");
        user.setPhone("+1234567890");
        user.setBirthDate(LocalDate.of(2000, 1, 1)); // Mayor de edad
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        Role role = new Role();
        role.setName(Constats.ROLE_AUX_BODEGA);

        when(rolePersistencePort.findByName(Constats.ROLE_AUX_BODEGA)).thenReturn(role);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encoded_password");

        userUseCase.registerUser(user);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userPersistencePort, times(1)).registerUser(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals("John", capturedUser.getName());
        assertEquals("Doe", capturedUser.getLastName());
        assertEquals("123456789", capturedUser.getDocument());
        assertEquals("+1234567890", capturedUser.getPhone());
        assertEquals(LocalDate.of(2000, 1, 1), capturedUser.getBirthDate());
        assertEquals("john.doe@example.com", capturedUser.getEmail());
        assertEquals("encoded_password", capturedUser.getPassword());
        assertEquals(Constats.ROLE_AUX_BODEGA, capturedUser.getRole().getName());
    }
}