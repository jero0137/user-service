package com.emazon.user_service.application.dto;

import com.emazon.user_service.Utils.RegexConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class RegisterDtoRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Pattern(regexp = RegexConstants.DOCUMENT_REGEX)
    private String document;

    @Pattern(regexp = RegexConstants.PHONE_REGEX)
    private String phone;

    @Past
    private LocalDate birthDate;

    @Email
    private String email;

    @NotBlank
    private String password;
}
