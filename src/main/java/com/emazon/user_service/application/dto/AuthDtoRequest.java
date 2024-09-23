package com.emazon.user_service.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthDtoRequest {
    private final String email;
    private final String password;
}
