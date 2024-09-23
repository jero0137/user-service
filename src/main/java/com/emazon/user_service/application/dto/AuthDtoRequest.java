package com.emazon.user_service.application.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthDtoRequest {
    @Email
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
}
