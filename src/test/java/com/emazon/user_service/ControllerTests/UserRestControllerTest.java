package com.emazon.user_service.ControllerTests;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.emazon.user_service.Config.TestSecurityConfig;
import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.application.handler.IUserHandler;
import com.emazon.user_service.infrastructure.configuration.Security.JwtConfig.JwtTokenProvider;
import com.emazon.user_service.infrastructure.input.UserRestController;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;


@WebMvcTest(UserRestController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {UserRestController.class, TestSecurityConfig.class})
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserHandler userHandler;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void registerUserWithValidDataReturnsCreatedStatus() throws Exception {
        RegisterDtoRequest request = new RegisterDtoRequest(
                "name",
                "lastname",
                "123456789",
                "+571234567894",
                LocalDate.of(2000, 1, 1),
                "email@emalil.com",
                "password"
        );

        mockMvc.perform(post("/user/register/aux_bodega")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(userHandler, times(1)).registerUser(any(RegisterDtoRequest.class));
    }

    @Test
    void registerUserWithInvalidDataReturnsBadRequestStatus() throws Exception {
        RegisterDtoRequest request = new RegisterDtoRequest(
                "name",
                "lastname",
                "12a456789",//Letter in document
                "+57123456a894",//Letter in phone
                LocalDate.of(2006, 1, 1),
                "email@emalil.com",
                "password"
        );

        mockMvc.perform(post("/user/register/aux_bodega")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(userHandler, never()).registerUser(any(RegisterDtoRequest.class));
    }
}