package com.emazon.user_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class RegisterDtoRequest {
    private String name;
    private String lastName;
    private String document;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
}
