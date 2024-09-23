package com.emazon.user_service.ControllerTests;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.emazon.user_service.Config.TestSecurityConfig;
import com.emazon.user_service.application.dto.AuthDtoRequest;
import com.emazon.user_service.application.dto.AuthDtoResponse;
import com.emazon.user_service.application.handler.IAuthenticationHandler;
import com.emazon.user_service.infrastructure.configuration.Security.JwtConfig.JwtTokenProvider;
import com.emazon.user_service.infrastructure.input.AuthRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(AuthRestController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {AuthRestController.class, TestSecurityConfig.class})
class AuthRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuthenticationHandler authenticationHandler;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void loginWithValidCredentialsReturnsOkStatus() throws Exception {
        AuthDtoRequest request = new AuthDtoRequest("validUsername@email.com", "validPassword");
        AuthDtoResponse response = new AuthDtoResponse("token");

        when(authenticationHandler.login(any(AuthDtoRequest.class))).thenReturn(response);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


    @Test
    void loginWithEmptyUsernameReturnsBadRequestStatus() throws Exception {
        AuthDtoRequest request = new AuthDtoRequest("", "validPassword");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void loginWithEmptyPasswordReturnsBadRequestStatus() throws Exception {
        AuthDtoRequest request = new AuthDtoRequest("validUsername@emial.com", "");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}