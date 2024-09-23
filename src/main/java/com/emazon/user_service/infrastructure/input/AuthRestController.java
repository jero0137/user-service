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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthRestController {

    private final IAuthenticationHandler authenticationHandler;

    @Operation(summary = "Login with credentials", description = "Authenticate user with provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<AuthDtoResponse> login(@Valid @RequestBody AuthDtoRequest authDtoRequest) {
        return ResponseEntity.ok(authenticationHandler.login(authDtoRequest));
    }

}
