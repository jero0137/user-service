package com.emazon.user_service.infrastructure.input;

import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.application.handler.IUserHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Register a new user", description = "Register a new user with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PostMapping("/register/aux_bodega")
    public ResponseEntity<Void> registerAuxBodega(@RequestBody @Valid RegisterDtoRequest registerDtoRequest) {
        userHandler.registerAuxBodega(registerDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/register/client")
    public ResponseEntity<Void> registerUserClient(@RequestBody @Valid RegisterDtoRequest registerDtoRequest) {
        userHandler.registerUserClient(registerDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
