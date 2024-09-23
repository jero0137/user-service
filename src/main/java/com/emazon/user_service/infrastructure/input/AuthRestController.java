package com.emazon.user_service.infrastructure.input;

import com.emazon.user_service.application.dto.AuthDtoRequest;
import com.emazon.user_service.application.dto.AuthDtoResponse;
import com.emazon.user_service.application.handler.IAuthenticationHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthRestController {

    private final IAuthenticationHandler authenticationHandler;

    @PostMapping("/login")
    public ResponseEntity<AuthDtoResponse> login(@Valid @RequestBody AuthDtoRequest authDtoRequest) {
        return ResponseEntity.ok(authenticationHandler.login(authDtoRequest));
    }

}
